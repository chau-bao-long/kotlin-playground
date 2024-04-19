package org.example

import java.util.concurrent.LinkedBlockingQueue

class BinarySearchTree<T: Comparable<T>> {
    private var root: TreeNode<T>? = null

    data class TreeNode<T>(
        var value: T,
        var left: TreeNode<T>? = null,
        var right: TreeNode<T>? = null,
        var type: NodeType? = null,
        var parent: TreeNode<T>? = null,
    )

    enum class NodeType(val value: String) {
        LEFT("l"),
        RIGHT("r"),
    }

    fun add(element: T) {
        if (root == null) {
            root = TreeNode(element)
            return
        }

        var current = root

        while (true) {
            if (current == null) break
            if (element < current.value) {
                if (current.left == null) {
                    current.left = TreeNode(
                        value = element, 
                        type = NodeType.LEFT,
                        parent = current,
                    )
                    break
                } else {
                    current = current.left
                }
            } else if (element > current.value) {
                if (current.right == null) {
                    current.right = TreeNode(
                        value = element, 
                        type = NodeType.RIGHT,
                        parent = current,
                    )
                    break
                } else {
                    current = current.right
                }
            } else {
                break
            }
        }
    }

     fun remove(element: T) {
         val node = search(element) ?: return
         node.parent?.apply { if (node.type == NodeType.LEFT) left = null else right = null }
         node.parent = null

         val buffer = LinkedBlockingQueue<TreeNode<T>>()
         if (node == root) {
             // In case of removing root node, pick left branch to be new root and add right branch
             root = node.left
             node.right?.let { buffer.put(it) }
         } else {
             node.left?.let { buffer.put(it) }
             node.right?.let { buffer.put(it) }
         }

         while (buffer.isNotEmpty()) {
             val cur = buffer.poll()
             add(cur.value)
             cur.left?.also { buffer.put(it) }
             cur.right?.also { buffer.put(it) }
         }
     }

     fun search(element: T): TreeNode<T>? {
         var current = root

         while (current != null) {
             if (element < current.value) {
                 current = current.left
             } else if (element > current.value) {
                 current = current.right
             } else {
                 return current
             } 
         }

         return null
     }

     /** Using breath-first search to print tree from top-down */
     fun printTree() {
         if (root == null) println("Empty tree") 

         var queue = LinkedBlockingQueue<TreeNode<T>>()
         var subQueue = LinkedBlockingQueue<TreeNode<T>>()
         queue.put(root!!)

         while (queue.isNotEmpty() || subQueue.isNotEmpty()) {
             while (queue.isNotEmpty()) {
                 val node = queue.poll()
                 print("${node.type?.value ?: ""}${node.value} ")
                 node.left?.let { subQueue.put(it) }
                 node.right?.let { subQueue.put(it) }
             }

             // Print new level
             println()

             // Swap queue
             queue = subQueue.also { subQueue = queue }
         }
     }
}
