package org.mobilenativefoundation.trails.xplat.core.circuit.api

import org.mobilenativefoundation.trails.xplat.feat.homeScreen.api.HomeScreen
import org.mobilenativefoundation.trails.xplat.feat.messagesScreen.api.MessagesScreen
import org.mobilenativefoundation.trails.xplat.feat.postScreen.api.PostScreen
import org.mobilenativefoundation.trails.xplat.feat.profileScreen.api.ProfileScreen
import org.mobilenativefoundation.trails.xplat.feat.searchScreen.api.SearchScreen

interface ScreenFactory {
    fun homeScreen(): HomeScreen
    fun messagesScreen(): MessagesScreen
    fun postScreen(): PostScreen
    fun profileScreen(): ProfileScreen
    fun searchScreen(): SearchScreen
}