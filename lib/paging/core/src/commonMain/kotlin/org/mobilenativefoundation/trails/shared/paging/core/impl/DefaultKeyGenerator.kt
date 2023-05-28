package org.mobilenativefoundation.trails.shared.paging.core.impl


import org.mobilenativefoundation.trails.shared.paging.core.Identifiable
import org.mobilenativefoundation.trails.shared.paging.core.KeyGenerator
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.PagingParams

internal class DefaultKeyGenerator<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> :
    KeyGenerator<Id, InCollection, AsSingle> {
    override fun fromSingle(value: AsSingle): PagingKey.Item<Id> = DefaultPagingKey.Item(value.id)
}

internal sealed class DefaultPagingKey<Id : Any> {
    data class Item<Id : Any>(
        override val id: Id
    ) : PagingKey.Item<Id>, DefaultPagingKey<Id>()

    data class Page<Id : Any>(
        override val params: PagingParams<Id>
    ) : PagingKey.Page<Id>, DefaultPagingKey<Id>()
}

internal data class DefaultPagingParams<Id : Any>(
    override val loadSize: Int,
    override val offset: Id?,
    override val type: PagingParams.Type
) : PagingParams<Id>
