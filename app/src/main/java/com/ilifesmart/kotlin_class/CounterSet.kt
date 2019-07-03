package com.ilifesmart.kotlin_class

class CounterSet<T> (val innerSet: MutableCollection<T> = HashSet<T>()): MutableCollection<T> by innerSet {
    var objectsAdd = 0

    override fun add(element: T): Boolean {
        objectsAdd++;
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdd += elements.size
        return innerSet.addAll(elements)
    }
}