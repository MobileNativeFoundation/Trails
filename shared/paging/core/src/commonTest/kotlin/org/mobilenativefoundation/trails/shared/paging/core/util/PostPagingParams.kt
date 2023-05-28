package org.mobilenativefoundation.trails.shared.paging.core.util

import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

data class PostPagingParams(
    override val loadSize: Int,
    override val after: Int?,
    override val type: PagingParams.Type
) : PagingParams<Int>
