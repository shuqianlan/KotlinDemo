package com.ilifesmart.kotlindemo

import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilifesmart.model.Person
import com.ilifesmart.model.PersonBean
import com.ilifesmart.model.PersonBean2
import com.ilifesmart.model.RectAngle
import com.ilifesmart.strings.join
import com.ilifesmart.strings.joinToString
import com.ilifesmart.strings.lastChar
import com.ilifesmart.strings.maxInt
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.lang.StringBuilder
import java.util.*

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
            println("index: $i")
        }

        // 开区间
        for(i in 1 until 10) { // == in 1..9
            println("until index: $i")
        }

        // 自定义步进值
        for(i in 100 downTo 1 step 1 ) {
            println("item index: $i"+ "type: " + fizzBuzz(i))
        }

        // map
        var binaryMap = TreeMap<Char, String>()
        for (c in 'A'..'F') {
            val binary = Integer.toBinaryString(c.toInt())
            // binaryMap.put(c, binary)
            binaryMap[c] = binary
        }

        for ((letter, binary) in binaryMap) {
            println("binaryMap key:$letter, value:$binary")
        }

        println("binaryMap: $binaryMap")

        // List
        val list = arrayListOf<Int>(1,2,3,4)
        for((index, element) in list.withIndex()) {
            println("list index:$index,element:$element")
        }

        // in区间判断
        println("in_region " + ("Kotlin" in "Java".."Scala"))
        println("in_region " + ("kotlin" in "Java".."Scala"))
        println("in_region " + ("Kotlin" in setOf<String>("Java", "Scala", "Kotlin")))

        // try-catch-finally
        try {
            val line = "not number"
            Integer.parseInt(line)
        } catch (e:NumberFormatException) {
            println("$e")
        } finally {
            println("end")
        }

        transferInt("not number")
        transferInt("256")

        chapter3()
        chapter4()
    }

    fun transferInt(i: String):Int? {
        val result = try {
            Integer.parseInt(i)
        } catch (e: NumberFormatException) {
            null
        }

        println("transferInt result $result")
        return result
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

    fun eval3(e: Expr): Int = when(e) {
        is Num -> e.value
        is Sum -> eval3(e.left) + eval3(e.right)
        else -> throw IllegalArgumentException("unknown expression.")
    }

    fun eval4(e: Expr): Int = when(e) {
        is Num -> e.value
        is Sum -> eval3(e.left) + eval3(e.right)
        else -> throw IllegalArgumentException("unknown expression.")
    }

    fun fizzBuzz(i: Int) = when {
        i % 3 == 0 -> "Pizz"
        i % 5 == 0 -> "Buzz"
        i % 15 ==0 -> "PizzBuzz"
        else -> "$i"
    }

    /* ------------------- chapter3 ------------------- */
    fun chapter3() {
        val strings = listOf<String>("first", "second", "third")
        println("strings last:${strings.last()} class:" +strings.javaClass )

        val numbers = setOf<Int>(1,2,3,4)
        println("numbers max:${numbers.max()} class:" + numbers.javaClass)

        val list = listOf(1, 2, 3, 4)
        var result = joinToString(list, "{ ", ", ", " }")
        println("result1 $result")

        // 显示表明参数名称
        result = joinToString(list, prefix = "{ ", separator = ", ", postfix = " }")
        println("result2 $result")

        // 显示表明参数名称，无序。
        result = joinToString(list, postfix = "{ ", separator = ", ", prefix = " }")
        println("result3 $result")

        println("max: ${maxInt(1,2)}")

        // 扩展函数
        println("Kotlin".lastChar())

        println("扩展函数: "+listOf("one","two","three").join(" "))

        val builder = StringBuilder("Kotlin?")
        builder.lastChar = '!' // setter函数调用
        println("StringBuilder $builder")

        // 正则表达式
        println("12.345-6.A".split(".", "-"))
        println("12.345-6.A".split("\\.|-".toRegex()))

        var path = "/Users/wuzh/kotlin/chapter.doc"
        var prefix = path.substringBeforeLast("/")
        var last   = path.substringAfterLast("/")
        var file   = last.substringBeforeLast(".")
        var category   = last.substringAfterLast(".")

        println("lists: ${listOf(path, prefix, last, file, category)}")

        // 三重引号的字符串(正则表达式放在三重引号的字符串中，其中字符不需转义\.而不是\\.)
        val regex = """(.+)/(.+)\.(.+)""".toRegex()
        val matchResult = regex.matchEntire(path)
        if (matchResult != null) {
            val (dictory, filename, extension) = matchResult.destructured
            println("lists: $dictory, $filename, $extension")
        }

    }

    /* ------------------- chapter4(对象与接口) ------------------- */
    fun chapter4() {
        Button().click()
        Button().AHaHaHa()
    }

    interface clickable {
        fun click()
        fun AHaHaHa() {
            println("Hahahahahhaha~")
        }
        fun BHaHaHa() {
            println("Hahahahahhaha~")
        }
    }

    interface focusable {
        fun AHaHaHa() {
            println("Hahahahahhaha~")
        }

        fun focus()
    }

    class Button: clickable, focusable {
        override fun click() {
            println("啦啦啦德玛西亚")
        }

        override fun focus() {
            println("focusable")
        }

        override fun AHaHaHa() {
            super<clickable>.AHaHaHa() //super<clickable> 基类的名字放在尖括号中
            super<focusable>.AHaHaHa()
        }
    }

}
