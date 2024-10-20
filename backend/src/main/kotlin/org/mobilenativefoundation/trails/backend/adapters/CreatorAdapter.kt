package org.mobilenativefoundation.trails.backend.adapters

import org.mobilenativefoundation.trails.backend.models.Creator

val CreatorAdapter = Creator.Adapter(
    platformAdapter = PlatformAdapter
)