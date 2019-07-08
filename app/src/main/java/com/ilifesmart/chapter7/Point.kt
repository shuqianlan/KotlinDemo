package com.ilifesmart.chapter7

import java.lang.IndexOutOfBoundsException

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x+other.x, y+other.y)

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is Point) {
            return false
        }
        return other.x ==x && other.y == y
    }

    operator fun get(index: Int): Int { // 两个参数就可以a[0,1]
        return when(index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException("invalid coordinate: $index")
        }
    }
}

operator fun Point.plus(other:Point): Point {
    return Point(x+other.x, y+other.y)
}