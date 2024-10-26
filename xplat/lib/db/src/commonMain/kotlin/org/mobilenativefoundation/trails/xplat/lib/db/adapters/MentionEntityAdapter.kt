package org.mobilenativefoundation.trails.xplat.lib.db.adapters

import org.mobilenativefoundation.trails.xplat.lib.db.MentionEntity

val MentionEntityAdapter = MentionEntity.Adapter(
    platformAdapter = PlatformAdapter

)