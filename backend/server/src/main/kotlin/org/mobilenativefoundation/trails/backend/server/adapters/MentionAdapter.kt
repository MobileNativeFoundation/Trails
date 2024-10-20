package org.mobilenativefoundation.trails.backend.server.adapters

import org.mobilenativefoundation.trails.backend.server.models.Mention

val MentionAdapter = Mention.Adapter(
    platformAdapter = PlatformAdapter
)