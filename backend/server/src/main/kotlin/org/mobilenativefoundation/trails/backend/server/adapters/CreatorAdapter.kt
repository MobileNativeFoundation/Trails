package org.mobilenativefoundation.trails.backend.server.adapters

import org.mobilenativefoundation.trails.backend.server.Creator

val CreatorAdapter = Creator.Adapter(
    platformAdapter = PlatformAdapter
)