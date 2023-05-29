package org.mobilenativefoundation.trails.shared.data.entity.flag

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val self: Link,
    val parent: Link? = null
)