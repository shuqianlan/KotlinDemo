package com.ilifesmart.kotlin

sealed class Expr2 {
    class Num(val value:Int): Expr2()
    class Sum(val left: Expr2, val right:Expr2): Expr2()

    companion object {
        fun eval(e:Expr2): Int =
            when(e) {
                is Expr2.Num -> e.value
                is Expr2.Sum -> eval(e.left) + eval(e.right)
            }
    }
}

