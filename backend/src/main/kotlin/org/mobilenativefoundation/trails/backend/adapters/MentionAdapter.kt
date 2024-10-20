package org.mobilenativefoundation.trails.backend.adapters

import org.mobilenativefoundation.trails.backend.models.Mention

val MentionAdapter = Mention.Adapter(
    platformAdapter = PlatformAdapter
)