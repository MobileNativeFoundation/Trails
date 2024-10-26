package org.mobilenativefoundation.trails.xplat.lib.db

import app.cash.sqldelight.db.SqlDriver
import me.tatarka.inject.annotations.Inject
import org.mobilenativefoundation.trails.xplat.lib.db.adapters.CreatorEntityAdapter
import org.mobilenativefoundation.trails.xplat.lib.db.adapters.MediaEntityAdapter
import org.mobilenativefoundation.trails.xplat.lib.db.adapters.MentionEntityAdapter
import org.mobilenativefoundation.trails.xplat.lib.db.adapters.PostEntityAdapter


interface TrailsDatabaseFactory {
    fun create(driver: SqlDriver): TrailsDatabase
}

@Inject
class RealTrailsDatabaseFactory : TrailsDatabaseFactory {
    override fun create(driver: SqlDriver): TrailsDatabase {
        return TrailsDatabase(
            driver = driver,
            creatorEntityAdapter = CreatorEntityAdapter,
            mediaEntityAdapter = MediaEntityAdapter,
            mentionEntityAdapter = MentionEntityAdapter,
            postEntityAdapter = PostEntityAdapter
        )
    }
}