package org.mobilenativefoundation.trails.shared.paging.core

interface Identifiable<out Id : Any> {
    val id: Id
}