package org.example

import kotlin.math.min
import kotlin.math.max

/**
 * Check if a string is unique or not?
 *
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
fun String.isUnique(): Boolean {
    val chars = toCharArray()
    val buffer = emptyHashMap<Char, Boolean>()

    for (i in chars.indices) {
        val char = chars[i]
        if (buffer[char] == true) return false
        buffer[char] = true
    }

    return true
}

/**
 * Check if a string is the permutation of the other.
 *
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
fun String.checkPermutation(other: String): Boolean {
    if (length != other.length) return false

    val buffer = IntArray(128) { 0 }

    for (i in this.indices) {
        buffer[this[i].code]++
    }

    for (i in other.indices) {
        buffer[other[i].code]--

        if (buffer[other[i].code] < 0) {
            return false
        }
    }

    return true
}

/**
 * A string can be inserted/removed/replaced a characters.
 * Determine if a string is one edit (zero edit) away from other string.
 *
 * Idea: brute force all cases
 * Time complexity: O(n)
 * Space complexity: O(1)
 *
 */
fun String.oneAwayEdit(other: String): Boolean {
    // Can replace one char to make string identical?
    if (length == other.length) {
        var countDiff = 0
        for (i in this.indices) {
            if (this[i] != other[i]) {
                countDiff++
            }
        }
        if (countDiff <= 1) {
            return true
        }
    }

    // Can remove one char to make string identical?
    for (i in this.indices) {
        val guess = substring(0, max(i, 0)) + substring(min(i + 1, length), length)

        if (guess == other) return true
    }

    // Can insert one char to make string identical?
    for (i in other.indices) {
        val guess = other.substring(0, max(i, 0)) + other.substring(min(i + 1, other.length), other.length)

        if (guess == this) return true
    }


    return false
}
