package org.mobilenativefoundation.trails.shared.paging.core.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.impl.extensions.get
import org.mobilenativefoundation.trails.shared.paging.core.Identifiable
import org.mobilenativefoundation.trails.shared.paging.core.Pager
import org.mobilenativefoundation.trails.shared.paging.core.PagingConfig
import org.mobilenativefoundation.trails.shared.paging.core.PagingConverter
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey
import org.mobilenativefoundation.trails.shared.paging.core.PagingState

class RealPager<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>>(
    private val store: Store<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>,
    private val converter: PagingConverter<Id, InCollection, AsSingle>,
    private val config: PagingConfig<Id>,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : Pager<Id, InCollection, AsSingle> {

    private val pagingStateFlow = MutableStateFlow<PagingState<Id, InCollection, AsSingle>>(PagingState.Initial)
    override val state: StateFlow<PagingState<Id, InCollection, AsSingle>> = pagingStateFlow

    private val requests = MutableSharedFlow<PagingKey<Id>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun load(pagingKey: PagingKey<Id>) {
        scope.launch {
            requests.emit(pagingKey)
        }
    }

    init {
        scope.launch {
            requests.emit(config.initialPagingKey)
            load()
        }
    }

    private suspend fun load() {
        requests.collect { request ->
            val data = store.get(request)
            if (data is PagingData.Page) {
                val currentPagingState = state.value
                val nextPages = when (currentPagingState) {
                    is PagingState.Data -> {
                        currentPagingState.pages.toMutableList().apply { add(data) }
                    }

                    PagingState.Initial -> listOf(data)
                }

                val newItems = data.data.map { converter.asPagingData(converter.from(it)) }

                val nextItems = when (currentPagingState) {
                    is PagingState.Data -> currentPagingState.items.toMutableList().apply { addAll(newItems) }
                    PagingState.Initial -> newItems
                }

                val nextPagingState = PagingState.Data(
                    items = nextItems,
                    pages = nextPages,
                    next = data.next
                )
                pagingStateFlow.value = nextPagingState
            }
        }
    }

}