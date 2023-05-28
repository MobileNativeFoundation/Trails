package org.mobilenativefoundation.trails.shared.paging.core

import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.trails.shared.paging.core.impl.PagingCache
import org.mobilenativefoundation.trails.shared.paging.core.impl.RealPagerBuilder

/**
 * Main entry point for creating a [Pager].
 */
interface PagerBuilder<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> {
    fun build(): Pager<Id, InCollection, AsSingle>

    fun store(store: Store<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>):
            PagerBuilder<Id, InCollection, AsSingle>

    fun converter(converter: PagingConverter<Id, InCollection, AsSingle>):
            PagerBuilder<Id, InCollection, AsSingle>

    fun config(config: PagingConfig<Id>): PagerBuilder<Id, InCollection, AsSingle>

    companion object {
        fun <Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> from(
            fetcher: Fetcher<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>,
            sourceOfTruth: SourceOfTruth<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>,
            memoryCache: PagingCache<Id, InCollection, AsSingle>
        ): PagerBuilder<Id, InCollection, AsSingle> = realPagerBuilder(
            fetcher = fetcher,
            sourceOfTruth = sourceOfTruth,
            memoryCache = memoryCache
        )

        operator fun <Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> invoke(
        ): PagerBuilder<Id, InCollection, AsSingle> = realPagerBuilder()
    }
}

private fun <Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> realPagerBuilder(
    fetcher: Fetcher<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>,
    sourceOfTruth: SourceOfTruth<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>,
    memoryCache: PagingCache<Id, InCollection, AsSingle>
): PagerBuilder<Id, InCollection, AsSingle> {
    return RealPagerBuilder(fetcher, sourceOfTruth, memoryCache)
}


private fun <Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> realPagerBuilder():
        PagerBuilder<Id, InCollection, AsSingle> {
    return RealPagerBuilder()
}





