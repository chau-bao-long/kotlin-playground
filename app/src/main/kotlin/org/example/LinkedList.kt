package org.example

data class Node<T>(val value: T, var next: Node<T>? = null)

class LinkedList<T>(vararg initialValue: T)  {
    private var root: Node<T>? = null

    init {
        lateinit var currentNode: Node<T>

        for ((i, value) in initialValue.withIndex()) {
            val node = Node(value)

            if (i == 0) {
                root = node 
            } else {
                currentNode.next = node
            }

            currentNode = node
        }
    }

    operator fun get(index: Int): T {
        if (index < 0) throw IndexOutOfBoundsException()

        var node = root
        var i = 0

        while (node != null) {
            if (i == index) return node.value

            node = node.next
            i++
        }

        throw IndexOutOfBoundsException()
    }

    fun add(value: T, index: Int) {
        if (index < 0) throw IndexOutOfBoundsException()

        var i = 0
        var node = root
        var prevNode: Node<T>? = null
        val insertNode = Node(value)

        while (i < index) {
            prevNode = node
            if (node == null) throw IndexOutOfBoundsException()
            node = node.next
            i++
        }

        if (node == root) {
            insertNode.next = root
            root = insertNode
        } else {
            insertNode.next = prevNode?.next
            prevNode?.next = insertNode
        }
    }

    fun remove(value: T) {
        var node = root
        var prevNode = root

        while (node != null) {
            if (node.value == value) {
                if (node == root) {
                    root = root?.next
                    prevNode = root
                    node = root
                } else {
                    prevNode?.next = node.next
                    node = prevNode?.next
                }
            } else {
                prevNode = node
                node = node.next
            }
        }
    }

    fun print() {
        var node = root

        while (node != null) {
            println(node.value)
            node = node.next
        }
    }
}
