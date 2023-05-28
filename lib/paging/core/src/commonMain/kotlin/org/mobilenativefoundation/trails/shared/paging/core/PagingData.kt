package org.mobilenativefoundation.trails.shared.paging.core

sealed interface PagingData<out Id : Any, out InCollection : Any, out AsSingle : Any> {
    interface Page<Id : Any, InCollection : Any, AsSingle : Any> : PagingData<Id, InCollection, AsSingle> {
        val params: PagingParams<Id>
        val data: List<InCollection>
        val next: PagingParams<Id>?
    }

    interface Item<Id : Any, InCollection : Any, AsSingle : Any> : PagingData<Id, InCollection, AsSingle> {
        val id: Id
        val data: AsSingle
    }
}
