package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.CreatorExtensions.asCreator
import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PostExtensions.asPost
import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.backend.models.CompositePost as CompositePostNetworkModel

object CompositePostExtensions {
    fun CompositePostNetworkModel.asCompositePost(): CompositePost {
        return CompositePost(
            post = this.post.asPost(),
            creator = this.creator.asCreator(),
            hashtags = listOf(),
            media = listOf(),
            mentions = listOf()
        )
    }
}
