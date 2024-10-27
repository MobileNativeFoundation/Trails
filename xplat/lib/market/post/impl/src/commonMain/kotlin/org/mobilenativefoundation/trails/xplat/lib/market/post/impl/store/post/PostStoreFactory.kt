package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.store.post

import kotlinx.coroutines.flow.flow
import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.*
import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity
import org.mobilenativefoundation.trails.xplat.lib.db.TrailsDatabase
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.CompositePostExtensions.asCompositePost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostExtensions.asNetworkModel
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostExtensions.asPost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostExtensions.asPostEntity
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.assembleCompositePost
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostQueriesExtensions.saveCompositePost
import org.mobilenativefoundation.trails.xplat.lib.models.post.CompositePost
import org.mobilenativefoundation.trails.xplat.lib.rest.api.operations.PostOperations
import org.mobilenativefoundation.trails.backend.models.CompositePost as CompositePostNetworkModel

@OptIn(ExperimentalStoreApi::class)
class PostStoreFactory(
    private val client: PostOperations,
    private val trailsDatabase: TrailsDatabase,
) {

    fun create(): PostStore {
        return MutableStoreBuilder.from(
            fetcher = createFetcher(),
            sourceOfTruth = createSourceOfTruth(),
            converter = createConverter()
        ).build(
            updater = createUpdater(),
            bookkeeper = null
        )
    }

    private fun createFetcher(): Fetcher<Int, CompositePostNetworkModel> =
        Fetcher.of { id ->
            // Fetch composite post from the network
            val networkCompositePost = client.getPost(id) ?: error(404)

            // Save individual posts and related entities
            trailsDatabase.postQueries.saveCompositePost(networkCompositePost.asCompositePost())

            networkCompositePost
        }

    private fun createSourceOfTruth(): SourceOfTruth<Int, PostEntity, CompositePost> =
        SourceOfTruth.of(
            reader = { id ->
                flow {
                    // Query the database for a post
                    emit(trailsDatabase.postQueries.assembleCompositePost(id.toLong()))
                }
            },
            writer = { _, postEntity ->
                trailsDatabase.postQueries.insertPost(postEntity)
            }
        )

    private fun createConverter(): Converter<CompositePostNetworkModel, PostEntity, CompositePost> =
        Converter.Builder<CompositePostNetworkModel, PostEntity, CompositePost>()
            .fromOutputToLocal { compositePost -> compositePost.post.asPostEntity() }
            .fromNetworkToLocal { networkPostModel -> networkPostModel.post.asPost().asPostEntity() }
            .build()

    private fun createUpdater(): Updater<Int, CompositePost, Boolean> =
        Updater.by(
            post = { _, compositePost ->
                val networkModel = compositePost.post.asNetworkModel()
                val succeeded = client.updatePost(networkModel)
                if (succeeded) {
                    UpdaterResult.Success.Typed(succeeded)
                } else {
                    UpdaterResult.Error.Message("Something went wrong.")
                }
            },
            onCompletion = null
        )


}