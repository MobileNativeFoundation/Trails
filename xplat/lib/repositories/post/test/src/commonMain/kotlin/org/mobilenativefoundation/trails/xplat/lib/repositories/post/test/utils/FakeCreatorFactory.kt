package org.mobilenativefoundation.trails.xplat.lib.repositories.post.test.utils

import org.mobilenativefoundation.trails.xplat.lib.models.post.Creator
import org.mobilenativefoundation.trails.xplat.lib.models.post.Platform

object FakeCreatorFactory {

    fun createCreator(id: Int) = Creator.Node(
        key = Creator.Key(id),
        properties = Creator.Properties(
            username = "",
            fullName = "",
            profilePicURL = "",
            isVerified = false,
            bio = "",
            platform = Platform.Instagram
        )
    )
}