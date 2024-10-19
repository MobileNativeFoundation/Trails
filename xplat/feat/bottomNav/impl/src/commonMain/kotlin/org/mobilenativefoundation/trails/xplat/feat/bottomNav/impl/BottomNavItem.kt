package org.mobilenativefoundation.trails.xplat.feat.bottomNav.impl

import org.mobilenativefoundation.trails.xplat.lib.carve.components.icons.Icon


enum class BottomNavItem(val title: String, val icon: Icon) {
    HOME("Home", Icon.HOME),
    SEARCH("Search", Icon.SEARCH),
    POST("Post", Icon.PLUS),
    INBOX("Inbox", Icon.MESSAGE),
    PROFILE("Profile", Icon.PROFILE),
}