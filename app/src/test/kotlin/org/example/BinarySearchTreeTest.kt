package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

class BinarySearchTreeTest {
    @Test
    fun add() {
        val tree = BinarySearchTree<Int>()
        tree.add(8)
        tree.add(3)
        tree.add(10)
        tree.add(1)
        tree.add(6)
        tree.add(14)
        tree.add(4)
        tree.add(7)
        tree.add(13)
        tree.printTree()
    }

    @Test
    fun search() {
        val tree = BinarySearchTree<Int>()
        tree.add(1)
        tree.add(2)
        tree.add(5)
        tree.add(3)
        tree.add(4)
        tree.printTree()
        assertNull(tree.search(6))
        assertEquals(5, tree.search(5)?.value)
        assertNotEquals(5, tree.search(3)?.value)
    }

    @Test
    fun remove() {
        val tree = BinarySearchTree<Int>()
        tree.add(1)
        tree.add(2)
        tree.add(5)
        tree.add(3)
        tree.add(4)

        val removedValue = arrayOf(1, 2, 3, 4)
        removedValue.forEach { tree.remove(it) }
        removedValue.forEach { assertNull(tree.search(it)) }
        assertEquals(5, tree.search(5)?.value)
    }
}

