package org.mobilenativefoundation.trails.shared.paging.core

import kotlinx.serialization.Serializable

interface PagingParams<out Id : Any> {
    val loadSize: Int
    val offset: Id?
    val type: Type

    @Serializable
    enum class Type {
        Refresh, Append, Prepend;

        companion object {
            private val names = values().associateBy { it.name }
            fun lookup(name: String) = names[name] ?: Append
        }
    }
}
