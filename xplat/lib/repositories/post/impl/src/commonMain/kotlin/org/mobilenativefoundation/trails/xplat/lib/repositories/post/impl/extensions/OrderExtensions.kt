package org.mobilenativefoundation.trails.xplat.lib.repositories.post.impl.extensions

import org.mobilenativefoundation.trails.xplat.lib.operations.query.Order


object OrderExtensions {
    fun Order<*>?.toDomain(): org.mobilenativefoundation.trails.xplat.lib.models.query.Order? {
        return this?.let {
            org.mobilenativefoundation.trails.xplat.lib.models.query.Order(
                propertyName = it.property.name,
                direction = it.direction,
                propertyType = it.propertyType
            )
        }
    }
}