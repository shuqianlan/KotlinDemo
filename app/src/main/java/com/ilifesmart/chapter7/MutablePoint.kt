package com.ilifesmart.chapter7

import java.lang.IndexOutOfBoundsException

data class MutablePoint(var x: Int, var y:Int) {
    operator fun set(index: Int, value: Int) {
        when(index) {
            0 -> x=value
            1 -> y = value
            else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
        }
    }
}