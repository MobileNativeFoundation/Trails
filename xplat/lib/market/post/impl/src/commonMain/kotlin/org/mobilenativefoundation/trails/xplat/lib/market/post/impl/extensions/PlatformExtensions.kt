package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.backend.models.Platform as PlatformNetworkModel


object PlatformExtensions {
    fun PlatformNetworkModel.asPlatform(): Platform {
        return when (this) {
            PlatformNetworkModel.TikTok -> Platform.TikTok
            PlatformNetworkModel.Instagram -> Platform.Instagram
        }
    }

    fun Platform.asNetworkModel(): PlatformNetworkModel {
        return when (this) {
            Platform.TikTok -> PlatformNetworkModel.TikTok
            Platform.Instagram -> PlatformNetworkModel.Instagram
        }
    }
}

