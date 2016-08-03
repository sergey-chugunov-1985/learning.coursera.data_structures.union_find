package org.chu.learning.coursera.shared.union_find.test

import org.chu.learning.coursera.shared.union_find.api.Identifiable
import org.chu.learning.coursera.shared.union_find.api.UnionFind
import org.chu.learning.coursera.shared.union_find.api.UnionFindFactory
import org.junit.Before
import org.junit.Test
import java.util.LinkedList
import java.util.stream.StreamSupport

private class IdentifiableInt(private val intVal: Int): Identifiable {
    override fun getId() = intVal
}

class UnionFindFactoryTest {

    private var unionFind: UnionFind? = null

    @Before fun setUp() {
        val sourceList: MutableList<Identifiable> = LinkedList()
        for (i in 0..9)
            sourceList.add(IdentifiableInt(i))

        unionFind = UnionFindFactory.newInstance().constructUnionFind(StreamSupport.stream((sourceList as LinkedList).spliterator(), false), sourceList.size)
    }

    @Test fun testCreateUnionFindFromStream() {
        assert(unionFind!!.clustersCount() == 10)
    }

    @Test fun testFindClusterForElement() {
        assert(unionFind!!.find(IdentifiableInt(5)).getId() == 5)
    }

    @Test fun testClustersCountOnUnion() {
        val c1 = unionFind!!.find(IdentifiableInt(4))
        val c2 = unionFind!!.find(IdentifiableInt(5))

        unionFind!!.union(c1, c2)

        assert(unionFind!!.clustersCount() == 9)
        assert(unionFind!!.find(IdentifiableInt(4)).getId() == 4)
        assert(unionFind!!.find(IdentifiableInt(5)).getId() == 4)
    }

    @Test fun testClusterElementsOnUnion() {
        val c1 = unionFind!!.find(IdentifiableInt(4))
        val c2 = unionFind!!.find(IdentifiableInt(5))

        unionFind!!.union(c1, c2)

        assert(unionFind!!.find(IdentifiableInt(4)).getId() == 4)
        assert(unionFind!!.find(IdentifiableInt(5)).getId() == 4)
    }

    @Test fun testClusterSizesOnUnion() {
        val c1 = unionFind!!.find(IdentifiableInt(4))
        val c2 = unionFind!!.find(IdentifiableInt(5))

        unionFind!!.union(c1, c2)

        assert(unionFind!!.find(IdentifiableInt(4)).size() == 2)
        assert(unionFind!!.find(IdentifiableInt(5)).size() == 2)
    }

    @Test fun testClustersRecursiveUnion() {
        val c4 = unionFind!!.find(IdentifiableInt(4))
        val c5 = unionFind!!.find(IdentifiableInt(5))

        unionFind!!.union(c4, c5)

        val c3 = unionFind!!.find(IdentifiableInt(3))
        val c5_1 = unionFind!!.find(IdentifiableInt(5))

        unionFind!!.union(c3, c5_1)

        val c5_2 = unionFind!!.find(IdentifiableInt(5))
        assert(c5_2.size() == 3)
    }
}