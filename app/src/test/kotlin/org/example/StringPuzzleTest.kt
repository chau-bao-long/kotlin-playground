package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class StringPuzzleTest {

    @Test
    fun isUnique() {
        assertTrue("abcdef".isUnique())
        assertFalse("aabdcef".isUnique())
        assertFalse("abdceff".isUnique())
        assertFalse("abddcef".isUnique())
    }

    @Test
    fun checkPermutation() {
        assertTrue("dog".checkPermutation("god"))
        assertFalse("dog".checkPermutation("god "))
        assertFalse("023".checkPermutation("321"))
        assertTrue("-023".checkPermutation("320-"))
    }

    @Test
    fun oneAwayEdit() {
        assertTrue("dog".oneAwayEdit("do"))
        assertTrue("do".oneAwayEdit("dog"))
        assertTrue("dog".oneAwayEdit("fog"))
        assertFalse("dogg".oneAwayEdit("do"))
        assertFalse("do".oneAwayEdit("done"))
        assertFalse("dog".oneAwayEdit("dad"))
    }
}
