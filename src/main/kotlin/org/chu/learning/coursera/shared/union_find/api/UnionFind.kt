package org.chu.learning.coursera.shared.union_find.api

interface UnionFind {
    fun find(element: Identifiable): Cluster

    fun union(c1: Cluster, c2: Cluster)

    fun clustersCount(): Int
}