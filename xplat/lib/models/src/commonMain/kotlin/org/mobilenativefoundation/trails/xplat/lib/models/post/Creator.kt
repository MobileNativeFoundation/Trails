package org.mobilenativefoundation.trails.xplat.lib.models.post

data class Creator(
    val id: Int,
    val username: String,
    val fullName: String?,
    val profilePicURL: String?,
    val isVerified: Boolean,
    val bio: String?,
    val platform: Platform
)