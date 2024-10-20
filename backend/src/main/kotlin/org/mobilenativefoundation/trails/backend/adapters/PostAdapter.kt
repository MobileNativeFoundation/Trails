package org.mobilenativefoundation.trails.backend.adapters

import org.mobilenativefoundation.trails.backend.models.Post

val PostAdapter = Post.Adapter(
    platformAdapter = PlatformAdapter
)