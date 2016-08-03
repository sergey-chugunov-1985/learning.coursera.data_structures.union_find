package org.chu.learning.coursera.shared.union_find.impl

import org.chu.learning.coursera.shared.union_find.api.Cluster
import org.chu.learning.coursera.shared.union_find.api.Identifiable
import org.chu.learning.coursera.shared.union_find.api.UnionFind

class UnionFindImpl(private val size: Int): UnionFind {

    private val elements: Array<UFElementImpl> = Array<UFElementImpl>(size, {i -> UFElementImpl()})

    private var clustersCount: Int = 0

    fun addElement(element: Identifiable) {
        val idToAdd = element.getId()

        if (element.getId() >= elements.size || element.getId() < 0)
            throw IllegalArgumentException("Adding elements with ids outside the size of UnionFind is not allowed")

        if (elements[idToAdd].isInitialized())
            throw IllegalArgumentException("Replacing elements is not allowed")

        elements[idToAdd].init(element)

        clustersCount++
    }

    override fun find(element: Identifiable) = elements[element.getId()].findCluster()

    override fun union(c1: Cluster, c2: Cluster) {
        if (c1 == c2) return

        if (c1.size() < c2.size())
            (c1 as ClusterImpl).mergeTo(c2 as ClusterImpl)
        else
            (c2 as ClusterImpl).mergeTo(c1 as ClusterImpl)

        clustersCount--
    }

    override fun clustersCount() = clustersCount
}