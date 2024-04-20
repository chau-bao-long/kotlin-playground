package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class QuickSortTest {
    @Test
    fun `sort normal array`() {
        val array = arrayOf(1, 2, 4, 3, 6, 5, 9, 7, 8)
        quickSort(array)
        assertContentEquals(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), array)
    }

    @Test
    fun `sort empty array`() {
        val array = emptyArray<Int>()
        quickSort(array)
        assertEquals(0, array.size)
    }

    @Test
    fun `sort increasing array`() {
        val array = arrayOf(-2, -1, 0, 1, 2)
        quickSort(array)
        assertContentEquals(arrayOf(-2, -1, 0, 1, 2), array)
    }

    @Test
    fun `sort duplicated items array`() {
        val array = arrayOf(1, 2, 1, 1, 1)
        quickSort(array)
        assertContentEquals(arrayOf(1, 1, 1, 1, 2), array)
    }

    @Test
    fun `sort array with single value`() {
        val array = arrayOf(3, 3, 3, 3, 3, 3)
        quickSort(array)
        assertContentEquals(arrayOf(3, 3, 3, 3, 3, 3), array)
    }
}

