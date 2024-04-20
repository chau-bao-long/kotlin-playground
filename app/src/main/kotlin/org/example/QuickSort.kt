package org.example

fun quickSort(array: Array<Int>) {
    if (array.size == 0) return

    quickSort(array, 0, array.size - 1)
}

fun quickSort(array: Array<Int>, start: Int, end: Int) {
    val index = sort(array, start, end)
    if (start < index - 1) quickSort(array, start, index - 1)
    if (end > index + 1) quickSort(array, index + 1, end)
}

fun sort(array: Array<Int>, start: Int, end: Int): Int {
    var left = start
    var right = end
    val pivot = array[(start + end) shr 1]

    while (left < right && array[left] != array[right]) {
        while (array[left] < pivot) {
            left++
        }
        while (array[right] > pivot) {
            right--
        }
        if (array[left] > array[right]) {
            array[left] = array[right].apply { array[right] = array[left] }
        }
    }

    return left
}

