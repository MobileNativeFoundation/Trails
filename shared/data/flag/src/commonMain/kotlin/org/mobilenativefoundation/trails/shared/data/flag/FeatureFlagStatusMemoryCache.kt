package org.mobilenativefoundation.trails.shared.data.flag

import org.mobilenativefoundation.store.cache5.Cache
import org.mobilenativefoundation.store.cache5.CacheBuilder
import org.mobilenativefoundation.store.cache5.MultiCache
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatus
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatusData

class FeatureFlagStatusMemoryCache : Cache<FeatureFlagStatusKey, FeatureFlagStatusData> {

    private val delegate = MultiCache<String, FeatureFlagStatus>(CacheBuilder())

    override fun getAllPresent(keys: List<*>): Map<FeatureFlagStatusKey, FeatureFlagStatusData> =
        mutableMapOf<FeatureFlagStatusKey, FeatureFlagStatusData>().apply {
            keys.filterIsInstance<FeatureFlagStatusKey>().forEach { key ->
                when (key) {
                    is FeatureFlagStatusKey.Collection -> delegate.getCollection(key.local())
                    is FeatureFlagStatusKey.Single -> delegate.getItem(key.local())
                }
            }
        }


    override fun invalidateAll() = delegate.invalidateAll()

    override fun size(): Long = delegate.size()

    override fun putAll(map: Map<FeatureFlagStatusKey, FeatureFlagStatusData>) {
        map.entries.forEach { (key, data) -> put(key, data) }
    }

    override fun put(key: FeatureFlagStatusKey, value: FeatureFlagStatusData) {
        when {
            key is FeatureFlagStatusKey.Collection && value is FeatureFlagStatusData.Collection -> {
                delegate.putCollection(key.local(), value.items)
            }

            key is FeatureFlagStatusKey.Single && value is FeatureFlagStatusData.Single -> {
                delegate.putItem(key.local(), value.item)
            }

            else -> throw UnsupportedOperationException()
        }
    }

    override fun invalidateAll(keys: List<FeatureFlagStatusKey>) {
        keys.forEach { invalidate(it) }
    }

    override fun invalidate(key: FeatureFlagStatusKey) {
        when (key) {
            is FeatureFlagStatusKey.Collection -> delegate.invalidateCollection(key.local())
            is FeatureFlagStatusKey.Single -> delegate.invalidateItem(key.local())
        }
    }

    override fun getOrPut(
        key: FeatureFlagStatusKey,
        valueProducer: () -> FeatureFlagStatusData
    ): FeatureFlagStatusData {

        val data = getIfPresent(key)

        return data ?: let {
            val produced = valueProducer()

            put(key, produced)

            produced
        }
    }

    override fun getIfPresent(key: FeatureFlagStatusKey): FeatureFlagStatusData? {
        return when (key) {
            is FeatureFlagStatusKey.Collection -> {
                val items = delegate.getCollection<List<FeatureFlagStatus>>(key.local())
                if (items != null) {
                    FeatureFlagStatusData.Collection(items)
                } else {
                    null
                }
            }

            is FeatureFlagStatusKey.Single -> {
                val item = delegate.getItem(key.local())
                if (item != null) {
                    FeatureFlagStatusData.Single(item)
                } else {
                    null
                }
            }
        }
    }

}