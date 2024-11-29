package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.sot

import dev.mokkery.MockMode
import dev.mokkery.matcher.eq
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.trails.xplat.lib.models.post.Post
import org.mobilenativefoundation.trails.xplat.lib.models.post.PostOutput
import org.mobilenativefoundation.trails.xplat.lib.operations.io.Operation.Query.QueryManyComposite
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.store.db.PostDAO
import org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.test_utils.FakePostFactory.createCompositePost
import kotlin.test.Test

class RealPostSourceOfTruthWriterTest {

    private val postDAO = mock<PostDAO>(MockMode.autoUnit)
    private val writer = RealPostSourceOfTruthWriter(postDAO)


    @Test
    fun queryManyComposite_givenNormalDAO_whenCalledWithValidValue_thenShouldSaveUsingDAO() = runTest {
        // Given
        val composite1 = createCompositePost(1)
        val composite2 = createCompositePost(2)
        val value = PostOutput.Collection(
            values = listOf(composite1, composite2)
        )

        val operation = QueryManyComposite(
            query = Query.Many<Post.Node>(
                DataSources.all,
                null,
                null,
                null
            ),
            dataSources = DataSources.all
        )

        // When
        writer.queryManyComposite(operation, value)

        // Then
        verifySuspend { postDAO.insertOneComposite(eq(composite1)) }
        verifySuspend { postDAO.insertOneComposite(eq(composite2)) }
    }
}