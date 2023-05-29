package org.mobilenativefoundation.trails.shared.data.flag

import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlagStatusData

interface FeatureFlagMarket {
    val featureFlagStatusStore: Store<FeatureFlagStatusKey, FeatureFlagStatusData>
    val featureFlagStore: Store<String, FeatureFlag>
}

