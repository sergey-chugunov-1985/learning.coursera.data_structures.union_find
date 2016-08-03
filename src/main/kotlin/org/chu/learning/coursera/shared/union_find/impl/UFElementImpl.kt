package org.chu.learning.coursera.shared.union_find.impl

import org.chu.learning.coursera.shared.union_find.api.Cluster
import org.chu.learning.coursera.shared.union_find.api.Identifiable

class UFElementImpl: Identifiable {

    private var payloadElement: Identifiable? = null

    private var cluster: ClusterImpl? = null

    private var initialized = false

    override fun getId() = payloadElement!!.getId()

    fun init(payload: Identifiable) {
        payloadElement = payload
        initCluster(payload)
        initialized = true
    }

    private fun initCluster(payload: Identifiable) {
        cluster = ClusterImpl(payload)
    }

    fun findCluster() = cluster!!.findCluster()

    fun isInitialized() = initialized
}