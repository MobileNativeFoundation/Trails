package org.mobilenativefoundation.trails.shared.paging.core.util

import org.mobilenativefoundation.trails.shared.paging.core.Identifiable

data class Post(
    override val id: Int,
    val content: String
) : Identifiable<Int>
