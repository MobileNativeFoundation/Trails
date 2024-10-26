package org.mobilenativefoundation.trails.xplat.lib.db.adapters

import org.mobilenativefoundation.trails.xplat.lib.db.PostEntity

val PostEntityAdapter = PostEntity.Adapter(
    platformAdapter = PlatformAdapter
)