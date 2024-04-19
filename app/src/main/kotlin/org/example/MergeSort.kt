package org.example

fun mergeSort(array: Array<Int>) {
    val buffer = arrayOfNulls<Int>(array.size)
    mergeSort(array, buffer, 0, array.size - 1)
}

fun mergeSort(array: Array<Int>, buffer: Array<Int?>, start: Int, end: Int) {
    if (start < end) {
        val mid = (start + end) shr 1
        mergeSort(array, buffer, start, mid)
        mergeSort(array, buffer, mid + 1, end)
        merge(array, buffer, start, end, mid)
    }
}

fun merge(array: Array<Int>, buffer: Array<Int?>, start: Int, end: Int, mid: Int) {
    for (i in start..end) {
        buffer[i] = array[i]
    }

    var left = start
    var right= mid + 1
    var cur = start

    while (left <= mid && right <= end) {
        if (buffer[left]!! < buffer[right]!!) {
            array[cur] = buffer[left]!!
            left++
        } else {
            array[cur] = buffer[right]!!
            right++
        }
        cur++
    }

    if (left <= mid) {
        for (i in 0..(mid - left)) {
            array[cur + i] = buffer[left + i]!!
        }
    }
}
