package org.mobilenativefoundation.trails.shared.paging.core.impl

import org.mobilenativefoundation.trails.shared.paging.core.PagingConfig
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

class DefaultPagingConfig<Id : Any> : PagingConfig<Id> {
    override val initialPagingKey: PagingKey<Id> = DefaultPagingKey.Page<Id>(
        params = DefaultPagingParams(
            loadSize = 20,
            after = null,
            type = PagingParams.Type.Append
        )
    )
}

