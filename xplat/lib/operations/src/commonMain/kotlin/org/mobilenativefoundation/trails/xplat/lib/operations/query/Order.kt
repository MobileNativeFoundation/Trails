package org.mobilenativefoundation.trails.xplat.lib.operations.query

import org.mobilenativefoundation.trails.xplat.lib.models.query.Direction
import org.mobilenativefoundation.trails.xplat.lib.models.query.Type
import kotlin.reflect.KProperty1


data class Order<T>(
    val property: KProperty1<T, *>,
    val direction: Direction,
    val propertyType: Type
)