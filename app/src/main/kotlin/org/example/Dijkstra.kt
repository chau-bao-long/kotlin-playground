package org.example

/**
 * Find the shortest path in directed graph with positive path weight.
 *
 * @param [graph] The map from node to other nodes with the weight.
 * @return [String] The visualization of cost with the path from begin to end.
 */
fun dijkstra(graph: Map<String, Map<String, Int>?>): String {
    val cost = mutableMapOf<String, Int?>()
    val parent = mutableMapOf<String, String?>()
    val processed = mutableMapOf<String, Boolean>()
 
    for (node in graph.keys.distinct()) {
        cost[node] = if (node == "begin") 0 else null
        processed[node] = false
    }

    var curNode = findLowestCostNode(cost, processed)
    while (curNode != null) {
        graph[curNode]?.let { destinations ->
            for ((destNode, destCost) in destinations) {
                val newCost = cost[curNode]!! + destCost

                if (cost[destNode] == null || cost[destNode]!! > newCost) {
                    cost[destNode] = newCost
                    parent[destNode] = curNode
                }
            }
        }
        processed[curNode] = true
        curNode = findLowestCostNode(cost, processed)
    }

    var trace = "end"
    var cur = "end"
    while (cur != "begin") {
        cur = parent[cur]!!
        trace += " <- $cur"
    }

    return "${cost["end"]} ($trace)"
}

fun findLowestCostNode(cost: Map<String, Int?>, processed: Map<String, Boolean>): String? {
    var lowestNode: String? = null

    for ((node, weight) in cost) {
        if (weight == null || processed[node]!!) continue
        if (lowestNode == null || weight < cost[lowestNode]!!) {
            lowestNode = node
        }
    }

    return lowestNode
}
