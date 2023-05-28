package org.mobilenativefoundation.trails.shared.paging.core


sealed class PagingState<out Id : Any, out InCollection : Identifiable<Id>, out AsSingle : Identifiable<Id>> {

    data class Data<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>>(
        val pages: List<PagingData.Page<Id, InCollection, AsSingle>>,
        val items: List<PagingData.Item<Id, InCollection, AsSingle>>,
        val next: PagingParams<Id>? = null
    ) : PagingState<Id, InCollection, AsSingle>()

    object Initial : PagingState<Nothing, Nothing, Nothing>()
}
