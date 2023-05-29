package org.mobilenativefoundation.trails.shared.data.db.translations

import org.mobilenativefoundation.trails.shared.data.db.FeatureFlagSq
import org.mobilenativefoundation.trails.shared.data.entity.flag.FeatureFlag

fun FeatureFlag.local() = FeatureFlagSq(
    key = key,
    id = id,
    name = name,
    description = description,
    kind = kind,
    version = version,
    creationDate = creationDate,
    variations = variations,
    tags = tags
)

fun FeatureFlagSq.output() = FeatureFlag(
    key = key,
    id = id,
    name = name,
    description = description,
    kind = kind,
    version = version,
    creationDate = creationDate,
    variations = variations,
    tags = tags
)