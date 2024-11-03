package org.mobilenativefoundation.trails.xplat.lib.operations.io

import org.mobilenativefoundation.trails.xplat.lib.models.Model
import org.mobilenativefoundation.trails.xplat.lib.operations.query.DataSources
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query.Many
import org.mobilenativefoundation.trails.xplat.lib.operations.query.Query.One


sealed class Operation<out K : Model.Key, out P : Model.Properties, out E : Model.Edges, out N : Model.Node<K, P, E>> {

    sealed class Mutation<out K : Model.Key, out P : Model.Properties, out E : Model.Edges, out N : Model.Node<K, P, E>> :
        Operation<K, P, E, N>() {
        sealed class Create<K : Model.Key, P : Model.Properties, E : Model.Edges> : Mutation<K, P, E, Nothing>() {
            data class InsertOne<P : Model.Properties>(
                val properties: P
            ) : Create<Nothing, P, Nothing>()
        }

        sealed class Update<out K : Model.Key, out P : Model.Properties, out E : Model.Edges, out N : Model.Node<K, P, E>> :
            Mutation<K, P, E, N>() {
            data class UpdateOne<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>>(
                val node: N
            ) : Update<K, P, E, N>()

            data class ReplaceOne<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>>(
                val node: N
            ) : Update<K, P, E, N>()
        }

        sealed class Upsert<K : Model.Key, P : Model.Properties, E : Model.Edges> : Mutation<K, P, E, Nothing>() {
            data class UpsertOne<P : Model.Properties>(
                val properties: P
            ) : Upsert<Nothing, P, Nothing>()
        }

        sealed class Delete<K : Model.Key> : Mutation<K, Nothing, Nothing, Nothing>() {
            data class DeleteOne<K : Model.Key>(
                val key: K
            ) : Delete<K>()

            data class DeleteMany<K : Model.Key>(
                val keys: List<K>
            ) : Delete<K>()

            data object DeleteAll : Delete<Nothing>()
        }
    }

    sealed class Query<out K : Model.Key, out P : Model.Properties, out E : Model.Edges, out N : Model.Node<K, P, E>> :
        Operation<K, P, E, N>() {
        abstract val dataSources: DataSources

        data class FindOne<K : Model.Key>(
            val key: K,
            override val dataSources: DataSources
        ) : Query<K, Nothing, Nothing, Nothing>()

        data class FindOneComposite<K : Model.Key>(
            val key: K,
            override val dataSources: DataSources
        ) : Query<K, Nothing, Nothing, Nothing>()


        data class ObserveOne<K : Model.Key>(
            val key: K,
            override val dataSources: DataSources
        ) : Query<K, Nothing, Nothing, Nothing>()


        data class ObserveOneComposite<K : Model.Key>(
            val key: K,
            override val dataSources: DataSources
        ) : Query<K, Nothing, Nothing, Nothing>()


        data class QueryOne<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>>(
            val query: One<N>,
            override val dataSources: DataSources
        ) : Query<K, P, E, N>()

        data class QueryMany<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>>(
            val query: Many<N>,
            override val dataSources: DataSources
        ) : Query<K, P, E, N>()

        data class QueryManyComposite<K : Model.Key, P : Model.Properties, E : Model.Edges, N : Model.Node<K, P, E>>(
            val query: Many<N>,
            override val dataSources: DataSources
        ) : Query<K, P, E, N>()

        data class FindMany<K : Model.Key>(
            val keys: List<K>,
            override val dataSources: DataSources
        ) : Query<K, Nothing, Nothing, Nothing>()

        data class FindAll(
            override val dataSources: DataSources
        ) : Query<Nothing, Nothing, Nothing, Nothing>()

        data class ObserveMany<K : Model.Key>(
            val keys: List<K>,
            override val dataSources: DataSources
        ) : Query<K, Nothing, Nothing, Nothing>()
    }


}