package org.chu.learning.coursera.shared.union_find.api

import org.chu.learning.coursera.shared.union_find.impl.UnionFindImpl
import java.util.stream.Stream

class UnionFindFactory {
    companion object {
        fun newInstance() = UnionFindFactory()
    }

    fun constructUnionFind(source: Stream<Identifiable>, sourceSize: Int): UnionFind {
        val uninitializedUnionFind = UnionFindImpl(sourceSize)

        source.forEach { i -> uninitializedUnionFind.addElement(i) }

        return uninitializedUnionFind
    }
}