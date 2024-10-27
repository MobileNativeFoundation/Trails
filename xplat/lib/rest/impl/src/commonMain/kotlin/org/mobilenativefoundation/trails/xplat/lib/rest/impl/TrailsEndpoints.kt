package org.mobilenativefoundation.trails.xplat.lib.rest.impl

internal object TrailsEndpoints {
    private const val ROOT_API_URL = "https://api.trails.mattramotar.dev"
    fun getPosts() = "$ROOT_API_URL/posts"
    fun getPost(id: Int) = "$ROOT_API_URL/posts/$id"
    fun updatePost(id: Int) = "$ROOT_API_URL/posts/$id"
}