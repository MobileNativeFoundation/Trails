package org.mobilenativefoundation.trails.shared.paging.core.impl

import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.trails.shared.paging.core.Identifiable
import org.mobilenativefoundation.trails.shared.paging.core.Pager
import org.mobilenativefoundation.trails.shared.paging.core.PagerBuilder
import org.mobilenativefoundation.trails.shared.paging.core.PagingConfig
import org.mobilenativefoundation.trails.shared.paging.core.PagingConverter
import org.mobilenativefoundation.trails.shared.paging.core.PagingData
import org.mobilenativefoundation.trails.shared.paging.core.PagingKey

class RealPagerBuilder<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>>(
    fetcher: Fetcher<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>? = null,
    sourceOfTruth: SourceOfTruth<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>? = null,
    memoryCache: PagingCache<Id, InCollection, AsSingle>? = null
) : PagerBuilder<Id, InCollection, AsSingle> {


    private var store: Store<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>? =
        if (fetcher != null && sourceOfTruth != null && memoryCache != null) {
            StoreBuilder.from(
                fetcher, sourceOfTruth, memoryCache
            ).build()
        } else {
            null
        }

    private var converter: PagingConverter<Id, InCollection, AsSingle> = DefaultPagingConverter()
    override fun build(): Pager<Id, InCollection, AsSingle> {
        store?.let {
            return RealPager(
                store = it,
                converter = converter,
                config = config

            )
        } ?: throw Exception("You must provide a Store.")
    }

    private var config: PagingConfig<Id> = DefaultPagingConfig()

    override fun converter(converter: PagingConverter<Id, InCollection, AsSingle>): PagerBuilder<Id, InCollection, AsSingle> {
        this.converter = converter
        return this
    }

    override fun config(config: PagingConfig<Id>): PagerBuilder<Id, InCollection, AsSingle> {
        this.config = config
        return this
    }

    override fun store(store: Store<PagingKey<Id>, PagingData<Id, InCollection, AsSingle>>): PagerBuilder<Id, InCollection, AsSingle> {
        this.store = store
        return this
    }

}
