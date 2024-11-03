package org.mobilenativefoundation.trails.xplat.lib.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object TrailsDispatchers {
    actual val io: CoroutineDispatcher = Dispatchers.IO
}