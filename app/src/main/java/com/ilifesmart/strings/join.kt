@file:JvmName("StringFunction") // 注解指定类名
package com.ilifesmart.strings

import java.lang.StringBuilder

// 顶层属性,
var ATOMIC_INTEGER = 0

//顶层函数被编译为静态函数.
// 含默认值的函数.
fun <T> joinToString(collection:Collection<T>,
                     prefix: String="{ ",
                     separator: String=", ",
                     postfix: String=" }"): String {
    val result = StringBuilder(prefix)
    for((index,element) in collection.withIndex()) {
        if (index > 0) {
            result.append(separator)

        }
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun maxInt(a:Int, b:Int): Int {
    return if (a > b) a else b
}

/* 可省略使用this，类似String的成员函数 */
fun String.lastChar(): Char{
    return get(this.length-1)
}

/* Collections扩展函数 */
fun <T> Collection<T>.joinToString(collection:Collection<T>,
                     prefix: String="{ ",
                     separator: String=", ",
                     postfix: String=" }"): String {
    val result = StringBuilder(prefix)
    for((index,element) in collection.withIndex()) {
        if (index > 0) {
            result.append(separator)

        }
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun Collection<String>.join(separator: String = ", ",
                            prefix: String="",
                            postfix: String="") = joinToString(separator, prefix, postfix)

/* 扩展属性 */

val String.lastChar: Char
get() = get(length-1) // getter (不可赋值，因此无默认getter实现)

var StringBuilder.lastChar: Char
get() = get(length-1)
set(value) = setCharAt(length-1, value)

infix fun Any.to(other: Any) = Pair(this, other)