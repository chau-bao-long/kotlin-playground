package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DijkstraTest {
    @Test
    fun simpleGraph() {
        val graph = mapOf(
            "begin" to mapOf(
                "a" to 1,
                "b" to 3,
            ),
            "a" to mapOf(
                "b" to 1,
                "end" to 5,
            ),
            "b" to mapOf("end" to 2),
            "end" to null,
        )

        val path = dijkstra(graph)

        assertEquals("4 (end <- b <- a <- begin)", path)
    }

    @Test
    fun graphFromBook() {
        val graph = mapOf(
            "begin" to mapOf(
                "lp" to 5,
                "poster" to 0,
            ),
            "lp" to mapOf(
                "guitar" to 15,
                "drums" to 20,
            ),
            "poster" to mapOf(
                "guitar" to 30,
                "drums" to 35,
            ),
            "drums" to mapOf(
                "end" to 10,
            ),
            "guitar" to mapOf(
                "end" to 20,
            ),
            "end" to null,
        )

        val path = dijkstra(graph)

        assertEquals("35 (end <- drums <- lp <- begin)", path)
    }

    @Test
    fun graphWithDeadendNode() {
        val graph = mapOf(
            "begin" to mapOf(
                "lp" to 5,
                "poster" to 0,
            ),
            "lp" to mapOf(
                "guitar" to 15,
                "drums" to 20,
            ),
            "poster" to mapOf(
                "guitar" to 30,
                "drums" to 35,
            ),
            "drums" to null,
            "guitar" to mapOf(
                "end" to 20,
            ),
            "end" to null,
        )

        val path = dijkstra(graph)

        assertEquals("40 (end <- guitar <- lp <- begin)", path)
    }

}
