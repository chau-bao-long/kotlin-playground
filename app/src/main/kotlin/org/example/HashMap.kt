package org.example

import kotlin.math.roundToInt


fun <K, V> emptyHashMap(): MyHashMap<K, V> {
    return MyHashMap()
}

fun <K, V> hashMapOf(vararg data: Pair<K, V>): MyHashMap<K, V> {
    return MyHashMap<K, V>().also {
        for (d in data) { it[d.first] = d.second }
    }
}

class MyHashMap<K, V>(
    initialCapacity: Int = 10,
    private val growFactor: Float = 1.5f,
) : Map<K, V> {
    private var table: Array<EntryNode<K, V>?> = arrayOfNulls(initialCapacity)
    private var growSize: Int = 0 

    override val entries: Set<Map.Entry<K, V>>
        get() = mutableSetOf<EntryNode<K, V>>().apply {
            for (entry in table) {
                entry?.loopNext { add(it) }
            }
        }

    override val size: Int
        get () = table.fold(0) { acc, entry ->
            var count = 0
            entry?.loopNext { count++ }
            acc + count
        }

    override val keys: Set<K>
        get() = table.fold(mutableSetOf()) { acc, entry ->
            entry?.loopNext { acc.add(it.key) }
            acc
        }

    override val values: Collection<V>
        get() = table.fold(mutableSetOf()) { acc, entry ->
            entry?.loopNext { acc.add(it.value) }
            acc
        }

    operator fun set(key: K, value: V) {
        put(key, value)
    }

    fun resize() {
        val newTable: Array<EntryNode<K, V>?> = arrayOfNulls((growSize * growFactor).roundToInt())

        for ((i, entry) in table.withIndex()) {
            newTable[i] = entry
        }

        table = newTable
    }

    fun put(key: K, value: V) {
        val hash = key.hashCode()
        val newNode = EntryNode(key, value)
        val index = computeIndex(hash)

        if (growSize >= table.size) resize()

        if (table[index] == null) {
            table[index] = newNode
            growSize++
        } else {
            run breaking@ {
                table[index]?.loopNext {
                    if (it.key == key) {
                        it.value = value
                        return@breaking
                    } else if (it.next == null) {
                        it.next = newNode
                        growSize++
                    }
                }
            }
        }
    }

    fun remove(key: K) {
        val hash = key.hashCode()
        val index = computeIndex(hash)
        var prevNode: EntryNode<K, V>? = table[index]

        table[index]?.loopNext {
            if (it.key != key) {
                prevNode = it
                return@loopNext
            }

            if (it == table[index]) {
                table[index] = table[index]?.next
            } else {
                prevNode?.next = it.next
            }
        }
    }

    override fun get(key: K): V? {
        val index = computeIndex(key.hashCode())
        var result: V? = null

        table[index]?.loopNext {
            if (it.key == key) result = it.value
        }

        return result
    }

    override fun containsKey(key: K): Boolean {
        val index = computeIndex(key.hashCode())
        var result = false

        table[index]?.loopNext {
            if (it.key == key) result = true
        }

        return result
    }

    override fun containsValue(value: V): Boolean {
        var found = false

        table.filterNotNull().forEach { entry ->
            entry.loopNext {
                if (it.value == value) found = true
            }
        }

        return found
    }

    override fun isEmpty(): Boolean {
        return table.filterNotNull().isEmpty()
    }

    private fun computeIndex(hash: Int): Int {
        return hash and (table.size - 1)
    }

    class EntryNode<K, V>(
        override val key: K,
        override var value: V,
        var next: EntryNode<K, V>? = null,
    ) : Map.Entry<K, V> {

        inline fun loopNext(fn: (node: EntryNode<K, V>) -> Unit) {
            var e: EntryNode<K, V>? = this

            while (e != null) {
                fn(e)
                e = e.next
            }
        }
    }
}

