package org.mobilenativefoundation.trails.shared.paging.core.impl

import org.mobilenativefoundation.trails.shared.paging.core.Identifiable
import org.mobilenativefoundation.trails.shared.paging.core.PagingConverter
import org.mobilenativefoundation.trails.shared.paging.core.PagingData

@Suppress("UNCHECKED_CAST")
class DefaultPagingConverter<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> :
    PagingConverter<Id, InCollection, AsSingle> {
    override suspend fun from(collection: InCollection): AsSingle = collection as AsSingle
    override fun asPagingData(value: AsSingle): PagingData.Item<Id, InCollection, AsSingle> =
        object : PagingData.Item<Id, InCollection, AsSingle> {
            override val id: Id = value.id
            override val data: AsSingle = value
        }
}
