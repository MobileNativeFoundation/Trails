package org.mobilenativefoundation.trails.backend.server.adapters

import org.mobilenativefoundation.trails.backend.server.Post

val PostAdapter = Post.Adapter(
    platformAdapter = PlatformAdapter
)