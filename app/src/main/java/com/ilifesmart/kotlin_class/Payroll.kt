package com.ilifesmart.kotlin_class

import java.io.File
import java.util.Comparator

/*
* 单例
*
* */
object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary(): Double {
        var sum: Double = 0.0
        for (person in allEmployees) {
            sum += person.money
        }
        return sum
    }
}

/*
* 若Java访问，则为CaseInsensitiveFileComparator.INSTANCE.compare(x1,x2)
* */
object CaseInsensitiveFileComparator: Comparator<File> {
    override fun compare(o1: File?, o2: File?): Int {
        if (o1 == null || o2 == null) {
            if (o1 != null) {
                return 1
            } else if (o2 != null) {
                return -1
            }
            return 0
        }

        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}