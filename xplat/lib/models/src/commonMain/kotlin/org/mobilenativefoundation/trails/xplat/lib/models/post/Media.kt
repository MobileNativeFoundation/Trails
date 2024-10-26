package org.mobilenativefoundation.trails.xplat.lib.models.post

data class Media(
    val id: Int,
    val mediaURL: String,
    val mediaType: MediaType,
    val mediaFormat: MediaFormat?,
    val duration: Int?,
    val altText: String?,
    val height: Int?,
    val width: Int?,
)