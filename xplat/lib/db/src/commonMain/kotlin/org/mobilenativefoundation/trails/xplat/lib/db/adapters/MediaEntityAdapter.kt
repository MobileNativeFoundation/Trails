package org.mobilenativefoundation.trails.xplat.lib.db.adapters

import org.mobilenativefoundation.trails.xplat.lib.db.MediaEntity

val MediaEntityAdapter = MediaEntity.Adapter(
    media_formatAdapter = MediaFormatAdapter,
    media_typeAdapter = MediaTypeAdapter

)