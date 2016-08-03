package org.chu.learning.coursera.shared.union_find.impl

import org.chu.learning.coursera.shared.union_find.api.Cluster
import org.chu.learning.coursera.shared.union_find.api.Identifiable

class ClusterImpl(private val topElement: Identifiable): Cluster {

    private var isRoot = true

    private var size = 1

    private var parentCluster: ClusterImpl? = null

    override fun getId() = topElement.getId()

    override fun size() = size

    fun findCluster(): ClusterImpl = if (isRoot) this else parentCluster!!.findCluster()

    fun mergeTo(cluster: ClusterImpl) {
        if (cluster.size < size)
            throw IllegalArgumentException("Merging bigger cluster into a smaller one is not allowed")

        isRoot = false
        parentCluster = cluster
        parentCluster!!.updateSize(this.size)
    }

    fun updateSize(size: Int) {
        this.size += size
        if (!isRoot)
            parentCluster!!.updateSize(size)
    }
}