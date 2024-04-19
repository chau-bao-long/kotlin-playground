package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HashMapTest {
    @Test
    fun runTest() {
        var h = emptyHashMap<String, Int>()

        assertTrue(h.isEmpty())

        h = hashMapOf(
            "test" to 1,
            "test2" to 2,
            "test3" to 3,
            "test4" to 4,
            "test5" to 5,
        )

        assertEquals(h["test"], 1)
        assertEquals(h["test2"], 2)
        assertEquals(h["test3"], 3)
        assertEquals(h["test4"], 4)
        assertEquals(h["test5"], 5)

        assertEquals(h.size, 5)

        h.remove("test2")
        h.remove("test3")
        h["test"] = 0

        assertEquals(h.size, 3)

        assertEquals(h["test2"], null)
        assertEquals(h["test5"], 5)
        assertEquals(h["test3"], null)

        assertFalse(h.containsKey("test3"))
        assertTrue(h.containsKey("test5"))

        assertFalse(h.containsValue(3))
        assertTrue(h.containsValue(5))

        assertFalse(h.isEmpty())
    }

    @Test
    fun testGrowSize() {
        val h = MyHashMap<Int, Int>(initialCapacity = 3, growFactor = 2.5f)
        h[1] = 1
        h[2] = 2
        h[3] = 3
        h[4] = 4
        h[5] = 5
    }
}

