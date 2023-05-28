package org.mobilenativefoundation.trails.shared.data.entity.paging

import kotlinx.serialization.Serializable
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams


@Serializable
data class TimelinePagingParams(
    override val loadSize: Int,
    override val offset: Int?,
    override val type: PagingParams.Type
) : PagingParams<Int>