package org.mobilenativefoundation.trails.xplat.lib.operations.query

data class DataSources(
    val memory: Boolean,
    val disk: Boolean,
    val remote: Boolean
) {
    companion object {
        val all = DataSources(memory = true, disk = true, remote = true)
        val localOnly = DataSources(memory = true, disk = true, remote = false)
        val remoteOnly = DataSources(memory = false, disk = false, remote = true)
    }
}


fun DataSources.isRemoteOnly(): Boolean =
    !this.memory && !this.disk && this.remote

fun DataSources.isLocalOnly(): Boolean =
    this.memory && this.disk && !this.remote

fun DataSources.isMemoryOnly(): Boolean =
    this.memory && !this.disk && !this.remote