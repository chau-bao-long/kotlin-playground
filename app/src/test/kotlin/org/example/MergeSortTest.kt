package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MergeSortTest {
    @Test
    fun `sort normal array`() {
        val array = arrayOf(1, 2, 4, 3, 6, 5, 9, 7, 8)
        mergeSort(array)
        assertContentEquals(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), array)
    }

    @Test
    fun `sort empty array`() {
        val array = emptyArray<Int>()
        mergeSort(array)
        assertEquals(0, array.size)
    }

    @Test
    fun `sort sorted array`() {
        val array = arrayOf(-2, -1, 0, 1, 2)
        mergeSort(array)
        assertContentEquals(arrayOf(-2, -1, 0, 1, 2), array)
    }
}

