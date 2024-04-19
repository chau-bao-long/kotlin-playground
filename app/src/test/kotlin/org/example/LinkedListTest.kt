package org.example

import kotlin.test.Test
import kotlin.test.assertEquals

class LinkedListTest {
    @Test
    fun `test linked list`() {
        val list = LinkedList(1, 2, 3, 4, 4, 5, 6, 4, 2, 1)
        list.remove(1)
        list.remove(2)
        assertEquals(list[0], 3)
    }
}

