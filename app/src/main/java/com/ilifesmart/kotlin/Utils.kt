@file:JvmName("KotlinUtils")
package com.ilifesmart.kotlin

fun Expr2.eval(e:Expr2): Int =
    when(e) {
        is Expr2.Num -> e.value
        is Expr2.Sum -> eval(e.left) + eval(e.right)
    }