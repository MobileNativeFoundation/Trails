package org.mobilenativefoundation.trails.xplat.lib.operations.query


sealed class Query<out T : Any> {
    abstract val dataSources: DataSources

    class One<T : Any>(
        override val dataSources: DataSources,
        val predicate: Predicate<T, *>?,
        val order: Order<T>?,
    ) : Query<T>()

    class Many<T : Any>(
        override val dataSources: DataSources,
        val predicate: Predicate<T, *>?,
        val order: Order<T>?,
        val limit: Int?
    ) : Query<T>()
}