package com.ilifesmart.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ilifesmart.chapter5.Person2
import com.ilifesmart.kotlin.Expr2
import com.ilifesmart.kotlin_class.Client
import com.ilifesmart.kotlin_class.LengthCounter
import com.ilifesmart.kotlin_class.Payroll
import com.ilifesmart.kotlin_interface.AddressUser
import com.ilifesmart.model.Person
import com.ilifesmart.model.PersonBean
import com.ilifesmart.model.PersonBean2
import com.ilifesmart.model.RectAngle
import com.ilifesmart.strings.join
import com.ilifesmart.strings.joinToString
import com.ilifesmart.strings.lastChar
import com.ilifesmart.strings.maxInt
import java.lang.AssertionError
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NullPointerException
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

    lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.hello)
        text.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("TextView is clicked.")
            }
        })
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
        chapter5()
        chapter6()
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

        println("=======================")

        com.ilifesmart.java.Button().getCurrentState()
        com.ilifesmart.kotlin.Button2().getCurrentState()
//        TextCanvas().print()
        val result = Expr2.eval(Expr2.Sum(Expr2.Sum(Expr2.Num(1), Expr2.Num(2)), Expr2.Num(3)))
        println("sealed Expr2 result: $result")

        val user = AddressUser("wuzhenghua")
        println("user.address ${user.address}")
        user.address = "杭州"
        println("user.address ${user.address}")
        println("user: $user")

        val counter = LengthCounter()
        counter.addWord("Hello,World!")
        println("LengthCounter.length: ${counter.counter}")

        val client = Client("杭州-滨江", 310052)
        println("client: $client")

        // 单例
        for (i in 1..10) {
            Payroll.allEmployees.add(com.ilifesmart.kotlin_class.Person("item:"+i, i+0.5))
        }

        println("Sum:${Payroll.calculateSalary()}")

        // 伴生对象
        val person = com.ilifesmart.kotlin_class.Person.fromJSON("")
        println("person.name:${person.name}")

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

        fun setFocus(b: Boolean)
    }

    // 默认是final，不可被继承
    class Button: clickable, focusable {
        var focusAble = false

        override fun click() {
            println("啦啦啦德玛西亚")
        }

        override fun setFocus(b: Boolean) {
            focusAble = b
        }

        override fun AHaHaHa() {
            super<clickable>.AHaHaHa() //super<clickable> 基类的名字放在尖括号中
            super<focusable>.AHaHaHa()
        }
    }

    // open:允许创建子类
    open class Button2: clickable, focusable {
        var focusAble = false

        override fun click() {
            println("啦啦啦德玛西亚")
        }

        override fun setFocus(b: Boolean) {
            focusAble = b
        }

        override fun AHaHaHa() {
            super<clickable>.AHaHaHa() //super<clickable> 基类的名字放在尖括号中
            super<focusable>.AHaHaHa()
        }
    }

    // 抽象成员不是默认open，但可以标记open
    abstract interface Canvas {
        abstract fun onDraw()

        open fun stopAnimation() {

        }

        fun animateTwice() {

        }
    }

    // 可见性修饰符: private(类中可见),protected(子类中可见),public(所有地方可见),internal(模块内可见) 【def:public】
    internal class TextCanvas: Canvas {
        override fun onDraw() {
            println("TextCanvas：--")
        }

        internal fun debug() {
            println("debug..")
        }

        private fun debug2() {
            println("debug2..")
        }
    }

// Kotlin禁止调用低可见性的类型
//    fun TextCanvas.print() {
//        debug() //
//        onDraw()
//    }

    fun chapter5() {
        var TAG = "Chapter5"
        val people = listOf(com.ilifesmart.chapter5.Person2("Jack", 28), com.ilifesmart.chapter5.Person2("Tom", 27))
        println("$TAG Max:${people.maxBy { it.age }}") // 如果上下文期望的是只有一个参数且其类型可以推断出来，就可以使用it
        println("$TAG Max:${people.maxBy(com.ilifesmart.chapter5.Person2::age)}") // 同上

        var names = people.joinToString(separator = " ", transform = {p:com.ilifesmart.chapter5.Person2 -> p.name})
        names = people.joinToString(separator = " "){p:com.ilifesmart.chapter5.Person2 -> p.name} // 显示标出类型
        names = people.joinToString(separator = " "){p -> p.name} // 上下文推导
        println("$TAG Names: $names")

        // lambda
        println("$TAG Max: ${people.maxBy { p:com.ilifesmart.chapter5.Person2 -> p.age }}")

        val sum = {x:Int,y:Int -> x+y}
        println("$TAG sum(2,1): ${sum(2,1)}")
        run {println("$TAG 42:"+ 42)} // 运行lambda中的代码

        fun printMessageWithPrefix(message: Collection<String>, prefix: String) {
            var count = 0 // 貌似其过程就是创建一个对象，然后修改对象的变量值.
            message.forEach {
                println("$TAG $prefix $it") // it:即上下文中推断出的变量声明.
                count++
            }
            println("$TAG count: $count")
        }
        printMessageWithPrefix(listOf("Tom", "Jack", "TomJ", "Hello", "TomJack"), "HAHA")

        fun salute() = println("$TAG Salute")
        run(::salute) //引用顶层函数 (不是类的成员)

        val createPerson = ::Person2 // 构造方法的引用
        val person = createPerson("wuzh", 28)
        println("$TAG person: $person")

        val predicate = person::isAdult // 引用扩展函数
        println("$TAG isAdult: ${predicate()}")

        val age = person::age
        println("$TAG age:${age()}") // 获取年龄

        // lambda： filter  map
        val beans = listOf(Person2("1", 10), Person2("2", 10), Person2("3", 12), Person2("4", 13), Person2("5", 14), Person2("6", 26), Person2("7", 28))
        println("$TAG names(age>12):${beans.filter { it.age>12 }.map { it.name }}")
        println("$TAG names:${beans.map(Person2::name)}")
        println("$TAG ages:${beans.map(Person2::age)}")
        println("$TAG age>11:${beans.filter { it.age > 11}}")

        // 对于HashMap filterKeys/filterValues
        // all, any, find, findOrNull, count
        val canBeInClub27 = {p:Person2 -> p.age <= 27}
        println("$TAG all matches: ${beans.all(canBeInClub27)}") // 所有元素是否满足表达式
        println("$TAG any matches: ${beans.any(canBeInClub27)}") // 至少一个元素是否满足表达式
        println("$TAG any matches count: ${beans.count(canBeInClub27)}") // 至少一个元素是否满足表达式

        // groupBy
        val rtn = beans.groupBy { it.age }   // : Map<Int, List<Person2>>
        println("$TAG rtn(groupByAge):$rtn") // rtn(groupByAge):{10=[Person2(name=1, age=10), Person2(name=2, age=10)], 12=[Person2(name=3, age=12)], 13=[Person2(name=4, age=13)], 14=[Person2(name=5, age=14)], 26=[Person2(name=6, age=26)], 28=[Person2(name=7, age=28)]}

        // flatMap
        val strings = listOf("abc", "def", "abcdefghijk")
        println("$TAG strings.list:${strings.flatMap { it.toList() }}")          // [a, b, c, d, e, f, a, b, c, d, e, f, g, h, i, j, k]
        println("$TAG strings.list:${strings.flatMap { it.toList() }.toSet()}")  // [a, b, c, d, e, f, g, h, i, j, k]

        // 懒惰序列(避免filter和map结果后新建的临时列表)
        println("$TAG sequence:${beans.asSequence().filter(canBeInClub27).toSet()}") // [Person2(name=1, age=10), Person2(name=2, age=10), Person2(name=3, age=12), Person2(name=4, age=13), Person2(name=5, age=14), Person2(name=6, age=26)]
        println("$TAG sequence:${beans.asSequence().filter(canBeInClub27)}")         // kotlin.sequences.FilteringSequence@7bf7895

        // 惰性集合操作
        println("toList:")
        listOf(5,6,7,8).asSequence().map { print(" $TAG map($it)"); it*it }.filter { print(":filter($it)"); it % 2 == 0 }.toList()
        println("nonToList:")
        listOf(1,2,3,4).asSequence().map { print(" $TAG map($it)"); it*it }.filter { print(":filter($it)"); it % 2 == 0 }
        println("endOfSequence")

        // 创建序列
        val natureNumbers = generateSequence(0) {it+1}
        val numberTo100 = natureNumbers.takeWhile { it <= 100 }
        println("$TAG 1+2+3+..+100=${numberTo100.sum()}") // 获取结果时，中间操作才开始执行.

        // lambda独特功能: with & apply

        // 此处多次使用result
        fun alphabet1(): String {
            val result = StringBuilder()
            for(letter in 'A'..'Z') {
                result.append(letter)
            }

            result.append("\nNow i know the alphabet!")
            return result.toString()
        }

        // lambda with
        fun alphabet2(): String {
            val result = StringBuilder()
            return with(result) {
                for(letter in 'A'..'Z') {
                    append(letter)
                }
                this.append("\nNow i know alphabet!")
                toString()
            }
        }

        val person3 = Person2("wuzh", 30)
        fun alphabet3() = with(StringBuilder()) {
            for(letter in 'A'..'Z') {
                append(letter)
            }
            this.append("\nNow i know alphabet!")
            // this@MainActivity.toString(): 引用外部应用的toString.
            toString() // 返回值，若返回值是接收者对象，则使用apply
        }

        fun alphabet4() = StringBuilder().apply {
            for(letter in 'A'..'Z') {
                append(letter)
            }
            this.append("\nNow i know alphabet!")
        }.toString()

        // buildString:创建StringBuilder并调用toString()
        fun alphabet5() = buildString {
            for(letter in 'A'..'Z') {
                append(letter)
            }
            this.append("\nNow i know alphabet!")
        }

        println("$TAG alphabet1:${alphabet1()}")
        println("$TAG alphabet2:${alphabet2()}")
        println("$TAG alphabet3:${alphabet3()}")
        println("$TAG alphabet4:${alphabet4()}")
        println("$TAG alphabet5:${alphabet5()}")
    }

    fun chapter6() {
        val TAG = "Chapter6"
        fun getStringLength(s: String): Int = s.length
        fun getStringLength(s: String?): Int = if (s!= null) s.length else 0
        fun getStringLength2_0(s: String?): Int? = s?.length
        fun getStringLength3_0(s: String?): Int = s?.length ?: 0

        println("$TAG Hello.length: ${getStringLength("Hello")}")
        println("$TAG null.length: ${getStringLength(null)}")
        println("$TAG Hello.length(2.0): ${getStringLength2_0("Hello")}")
        println("$TAG null.length(2.0): ${getStringLength2_0(null)}")
        println("$TAG Hello.length(3.0): ${getStringLength3_0("Hello")}")
        println("$TAG null.length(3.0): ${getStringLength3_0(null)}")

        fun managerName(employee: Employee): String? = employee.manager?.name
        val ceo = Employee("Tom Boss", null)
        val cto = Employee("Jack Willson", ceo)

        println("$TAG ceo.manager ${managerName(ceo)}")
        println("$TAG cto.manager ${managerName(cto)}")

        fun CompanyPerson.countryName(): String {
            val country = this.company?.address?.country
            return if (country != null) country else "unKnown Area"
        }

        fun CompanyPerson.countryName2_0(): String {
            val country = this.company?.address?.country
            return country ?: "unKnown Area"
        }

        fun CompanyPerson.countryName3_0() = this.company?.address?.country ?: "unKnown Area"

        // ? ?:
        fun printShippingLabel(person: CompanyPerson) {
            val address = person.company?.address ?: throw IllegalArgumentException("$TAG No Address")
            with(address) {
                println("$TAG $streetAddress")
                print(" $zipCode $city $country")
            }
        }

        val person = CompanyPerson("wuzh", null)
        println("$TAG wuzh.country ${person.countryName()}")
        println("$TAG wuzh.country(2.0) ${person.countryName2_0()}")
        println("$TAG wuzh.country(3.0) ${person.countryName3_0()}")
        try {
            print("$TAG Label:${printShippingLabel(person)}")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        // !!
        fun ignoreNulls(s:String?) {
            val sNotNull = s!!
            println("$TAG length:${sNotNull.length}")
        }

        try {
            ignoreNulls(null)
        } catch (ex:NullPointerException) {
            ex.printStackTrace() // NullPointerException
        }

        // let
        fun sendEmailTo(email: String?) {
            println("$TAG sendEmailTo: $email")
        }

        var email = "351563763@qq.com"
        email?.let { email -> sendEmailTo(email) }

        val email2 = null
        email2?.let { sendEmailTo(it) }

        fun getBestPersonInTheWorld(): CompanyPerson? = null
        fun getBestPersonInTheWorld2(): CompanyPerson? = CompanyPerson("Jack", null)
        getBestPersonInTheWorld()?.let { println("$TAG ${it.name}: The Best Man in the world!") }
        getBestPersonInTheWorld2()?.let { println("$TAG ${it.name}: The Best Man in the world!") }

        println("$TAG -------------------------- end_of_let --------------------------")

        val test = MyServiceDemo()
        try {
            test.testAction()
            test.setUp()
            test.testAction()
        } catch (ex:Exception) {
            ex.printStackTrace()
        }

        // 可空类型的扩展
        fun verifyUserInput(input: String?) {
            if (input.isNullOrBlank()) {
                println("$TAG Please fill in the required fields.")
            }
        }

        verifyUserInput(null)

        val answer:Any = 42
        val i = 1
        val j:Long = 1.toLong() // 数字转换必须是显示转换
        "42".toInt()
        "42".toIntOrNull()

        val list = listOf<Int?>(1,2,3,null,4,5,6)
        println("$TAG listWithNull:$list")
        println("$TAG listFilterNull:${list.filterNotNull()}") // 返回值类型为:List<T>


        val arrays = arrayOf(1,2,3,4,5,6)

        for (i in arrays.indices) {
            println("$TAG [index:$i,value:${arrays[i]}]")
        }

        val alphabets = Array<String>(26) {('a'+it).toString()}
        println("$TAG alphabets:${alphabets.joinToString("")}")
        println("$TAG arrays:${arrays.joinToString(", ","[","]")}")

        val strings = listOf("a", "b", "c")
        println("$TAG %s/%s/%s".format(*strings.toTypedArray())) //

        val int_1 = IntArray(5)
        val int_2 = intArrayOf(1,2,3,4,5)
        val int_3 = IntArray(5) {(it+1)*(it+1)}

        println("$TAG int_1(def):    ${int_1.joinToString(", ", "[", "]")}")
        println("$TAG int_2(factory):${int_2.joinToString(", ", "[", "]")}")
        println("$TAG int_3(lambda): ${int_3.joinToString(", ", "[", "]")}")

        Array<String>(5) {"item-"+(1+it)}.forEachIndexed { index, s -> println("$TAG index:$index,content: $s") }

    }

    class Employee(val name: String, val manager: Employee?)

    class Address(val streetAddress:String, val zipCode:Int, val city:String, val country: String)

    class Company(val name:String, val address: Address?)

    class CompanyPerson(val name: String, val company: Company?) {
        override fun equals(o: Any?): Boolean {
            val other = o as? CompanyPerson ?: return false // 如果不为CompangPerson类型则返回false.
            return super.equals(other)
        }
    }

    class MyService {
        fun performAction(): String = "foo"
    }

    class MyServiceDemo {
        lateinit var service:MyService

        fun setUp() {
            service = MyService()
        }

        fun testAction() {
            println("Chapter6 ${service.performAction()}")
        }
    }

    interface Processor<T> {
        fun progress(): T
    }

    // Unit : Kotlin中的void
    class NoResultProcessor: Processor<Unit> {
        override fun progress() {
            // 此时就不需要书写返回值. 比Java优雅~
        }
    }
}
