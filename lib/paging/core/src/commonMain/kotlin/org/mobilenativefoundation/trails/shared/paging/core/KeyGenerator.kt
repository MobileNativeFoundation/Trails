package org.mobilenativefoundation.trails.shared.paging.core

import org.mobilenativefoundation.trails.shared.paging.core.impl.DefaultKeyGenerator

interface KeyGenerator<Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> {
    fun fromSingle(value: AsSingle): PagingKey.Item<Id>

    companion object {
        fun <Id : Any, InCollection : Identifiable<Id>, AsSingle : Identifiable<Id>> default():
                KeyGenerator<Id, InCollection, AsSingle> = DefaultKeyGenerator<Id, InCollection, AsSingle>()
    }
}
