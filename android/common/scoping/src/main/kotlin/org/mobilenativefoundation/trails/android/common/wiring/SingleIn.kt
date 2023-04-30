package org.mobilenativefoundation.trails.android.common.wiring

import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SingleIn(val scope: KClass<*>)