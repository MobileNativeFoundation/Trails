package org.mobilenativefoundation.trails.xplat.lib.models.post

sealed class PostWriteResponse {
    data class Create(val key: Post.Key?) : PostWriteResponse()
    data class Update(val count: Int) : PostWriteResponse()
    data class Delete(val count: Int) : PostWriteResponse()
    data class Upsert(val key: Post.Key?) : PostWriteResponse()
}