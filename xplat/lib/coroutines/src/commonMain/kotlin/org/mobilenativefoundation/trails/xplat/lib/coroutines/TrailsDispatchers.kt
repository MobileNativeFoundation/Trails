package org.mobilenativefoundation.trails.xplat.lib.coroutines

import kotlinx.coroutines.CoroutineDispatcher

expect object TrailsDispatchers {
    val io: CoroutineDispatcher
}