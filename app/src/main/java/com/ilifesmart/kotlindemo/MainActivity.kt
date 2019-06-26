package com.ilifesmart.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilifesmart.model.Person
import com.ilifesmart.model.PersonBean
import com.ilifesmart.model.PersonBean2
import com.ilifesmart.model.RectAngle
import java.lang.Exception
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    enum class Color(
        val r: Int, val g: Int, val b: Int // 声明枚举常量的属性
    ) {
        // 常量指定值
        RED(255, 0, 0),
        ORANGE(255, 165, 0),
        YELLOW(255, 255, 0),
        GREEN(0, 255, 0),
        BLUE(0, 0, 255),
        WHITE(255, 255, 255);

        fun rgb() = (r*256 + g) * 256 + b // 定义枚举常量的方法
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = 1
        val b: Int = 2
        val c = a+b;
        val d: String
        val e: Int
        e = 257
        var f = 12
        f = 13
        f = 14

        if (a > 1) {
            d = "Hello"
        } else {
            d = "Jni"
        }

        if (d is String) {
            println("upper " + d.toUpperCase())
        }

        println("a: $a")
        println("b: $b")
        println("(1+2)=$c")
        println("(1+2)=$a+$b")
        println("(1+2)=${a+b}")
        Person().main();

        println("max(1,2) " + max(1, 2))
        println("max2(1,2) " + max2(1, 2))
        println("max3(1,2) " + max3(1, 2))

        var bean = PersonBean()
        bean.age = 26
        bean.name = "Tom"
        println("personBean $bean")

        val bean2 = PersonBean2("Jack", 30)
        println("name:" + bean2.name)
        println("age:" + bean2.age)

        val rectAngle = RectAngle(40, 40)
        println("isSquare " + rectAngle.isSquare)

        val rectAngle2 = RectAngle(40, 41)
        println("isSquare " + rectAngle2.isSquare)

        val result = eval(Sum(Sum(Num(1), Num(2)), Num(3)))
        println("Sum(Sum(Num(1), Num(2)), Num(3)) result: $result")

        // 闭区间
        for(i in 1..10) {
            println("item index: $i")
        }
        
    }

    fun max(a:Int, b:Int): Int {
        if (a > b) {
            return a
        } else {
            return b;
        }
    }

    // 单个表达式可以省略{}, 表达式体函数
    fun max2(a:Int, b:Int): Int = if(a > b) a else b

    // 单个表达式可以省略{}, 表达式体函数
    fun max3(a:Int, b:Int) = if(a > b) a else b

    fun getMnemonic(color: Color) = when (color) {
        Color.RED -> "RED"
        Color.ORANGE -> "ORANGE"
        Color.YELLOW -> "YELLOW"
        Color.GREEN -> "GREEN"
        Color.BLUE -> "BLUE"
        Color.WHITE -> "WHITE"
    }

    fun getWarmth(color: Color) = when (color) {
        Color.RED, Color.BLUE, Color.GREEN -> "Wram"
        Color.ORANGE, Color.WHITE, Color.YELLOW -> "Colder"
    }

    fun mix(c1: Color, c2: Color) = when(setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.RED
        setOf(Color.YELLOW, Color.ORANGE) -> Color.GREEN
        setOf(Color.ORANGE, Color.GREEN) -> Color.RED
        setOf(Color.GREEN, Color.BLUE) -> Color.RED
        setOf(Color.BLUE, Color.WHITE) -> Color.RED
        else -> throw Exception("Dirty color") // 匹配不到的默认
    }

    fun mix2(c1: Color, c2: Color) = when {
        (c1 == Color.GREEN) && c2 == Color.WHITE -> "Green"
        else -> throw Exception("Dirty Color.")
    }

    // ((1+2)+3) + 4 ..
    interface Expr
    class Num(val value: Int): Expr // 实现了接口
    class Sum(var left: Expr, var right: Expr): Expr // 实现了接口

    fun eval(e: Expr): Int {
        if (e is Num) {
            return e.value
        }

        if (e is Sum) {
            return eval(e.left) + eval(e.right)
        }

        throw IllegalArgumentException("unknown expression")
    }

    fun eval2(e: Expr): Int =
        if (e is Num) {
            e.value
        } else if (e is Sum) {
            eval(e.left) + eval(e.right)
        } else {
            throw IllegalArgumentException("unknown expression")
        }

    fun eval3(e: Expr): Int =when(e) {
        is Num -> e.value
        is Sum -> eval3(e.left) + eval3(e.right)
        else -> throw IllegalArgumentException("unknown expression.")
    }
}
