package org.mobilenativefoundation.trails.xplat.lib.models.query

import kotlin.reflect.KProperty1

data class Order<T>(
    val property: KProperty1<T, Comparable<*>>,
    val direction: Direction
)