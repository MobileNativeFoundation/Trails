package org.mobilenativefoundation.trails.android.feat.hike

import kotlinx.coroutines.flow.Flow
import org.mobilenativefoundation.trails.shared.data.entity.LatLng

interface LocationManager {
    fun streamLocation(): Flow<LatLng>
}