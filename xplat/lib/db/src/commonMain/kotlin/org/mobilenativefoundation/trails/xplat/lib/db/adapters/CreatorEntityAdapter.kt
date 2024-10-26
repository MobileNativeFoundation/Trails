package org.mobilenativefoundation.trails.xplat.lib.db.adapters

import org.mobilenativefoundation.trails.xplat.lib.db.CreatorEntity

val CreatorEntityAdapter = CreatorEntity.Adapter(
    platformAdapter = PlatformAdapter
)