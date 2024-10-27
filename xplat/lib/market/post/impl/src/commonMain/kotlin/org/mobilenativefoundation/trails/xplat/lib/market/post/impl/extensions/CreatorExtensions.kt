package org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions

import org.mobilenativefoundation.trails.xplat.lib.market.post.impl.extensions.PlatformExtensions.asPlatform
import org.mobilenativefoundation.trails.xplat.lib.models.post.*
import org.mobilenativefoundation.trails.backend.models.Creator as CreatorNetworkModel


object CreatorExtensions {

    fun CreatorNetworkModel.asCreator(): Creator {
        return Creator(
            id = this.id,
            username = this.username,
            fullName = fullName,
            profilePicURL = profilePicUrl,
            isVerified = isVerified,
            bio = bio,
            platform = platform.asPlatform()
        )
    }

}