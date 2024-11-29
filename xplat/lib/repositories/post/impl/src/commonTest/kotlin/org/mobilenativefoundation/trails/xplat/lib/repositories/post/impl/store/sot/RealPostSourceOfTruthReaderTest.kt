package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.sot

import app.cash.turbine.test
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.eq
import dev.mokkery.mock
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation.Query.FindOne
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions.PostExtensions.asNode
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.PostDAO
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.test.utils.FakePostFactory.createCompositePost
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.test.utils.FakePostFactory.createPostEntity
import kotlin.test.Test
import kotlin.test.assertEquals

class RealPostSourceOfTruthReaderTest {
    private val postDAO = mock<PostDAO>()
    private val predicateEvaluator = RealPostPredicateEvaluator()
    private val comparator = RealPostComparator()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val reader = RealPostSourceOfTruthReader(
        postDAO = postDAO,
        predicateEvaluator = predicateEvaluator,
        comparator = comparator,
        coroutineDispatcher = testDispatcher
    )

    @Test
    fun findOne_givenNormalDAO_whenCalledAndEntityExists_thenShouldEmitOutput() = testScope.runTest {
        // Given
        val flow = MutableSharedFlow<PostOutput?>(10)
        val key = Post.Key(1)
        val operation = FindOne(key, dataSources = DataSources.all)
        val postEntity = createPostEntity(1)

        everySuspend { postDAO.findOne(eq(1)) }.returns(postEntity)

        // When
        reader.findOne(operation) { flow.emit(it) }

        // Then
        flow.test {
            val output = awaitItem()
            assertEquals(PostOutput.Single(postEntity.asNode()), output)
        }
    }

    @Test
    fun queryManyComposite_givenNormalDAO_whenCalledAndEntitiesExist_thenShouldEmitOutput() = testScope.runTest {
        // Given
        val flow = MutableSharedFlow<PostOutput?>(10)
        val query = Query.Many<Post.Node>(
            dataSources = DataSources.all,
            predicate = null,
            order = null,
            limit = null
        )
        val entity1 = createPostEntity(1)
        val entity2 = createPostEntity(2)
        val composite1 = createCompositePost(1)
        val composite2 = createCompositePost(2)

        everySuspend { postDAO.findAll() }.returns(listOf(entity1, entity2))
        everySuspend { postDAO.findOneAndAssembleComposite(eq(1)) }.returns(composite1)
        everySuspend { postDAO.findOneAndAssembleComposite(eq(2)) }.returns(composite2)


        // When
        reader.queryManyComposite(query) { flow.emit(it) }

        // Then
        flow.test {
            val output = awaitItem()
            assertEquals(PostOutput.Collection(listOf(composite1, composite2)), output)
        }
    }
}