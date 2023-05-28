package org.mobilenativefoundation.trails.shared.paging.core

import kotlinx.coroutines.flow.StateFlow

interface Pager<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> {
    val state: StateFlow<PagingState<Id, InCollection, AsSingle>>
    fun load(pagingKey: PagingKey<Id>)
}