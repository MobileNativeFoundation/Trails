package org.mobilenativefoundation.trails.xplat.lib.store

interface Factory<T: Any> {
    fun create(): T
}