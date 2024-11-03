package org.mobilenativefoundation.trails.xplat.lib.models

interface Model<out K : Model.Key, out P : Model.Properties, out E : Model.Edges> {

    interface Key
    interface Properties
    interface Edges

    interface Node<out K : Key, out P : Properties, out E: Edges> : Model<K, P, E> {
        val key: K
        val properties: P
    }

    interface Composite<K : Key, P : Properties, E : Edges> : Model<K, P, E> {
        val node: Node<K, P, E>
        val edges: E
    }
}
