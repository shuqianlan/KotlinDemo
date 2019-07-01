# kotlin语法

## 静态类型的编程语言

未指定类型在初次初始化的时候确定类型，后期不可再度更改

- : Int(类型声明)
- val age:Int? = null //(可空类型)
- x ?: 0 （如果x为null，则Elvis运算符会返回0）
- max(a:Int, b:Int): Int (返回类型: Int)
- val(类似const,仅能一次初始化)， var(非const)
- ${a+b} (将a+b结果转输出，可用于复杂表达式) (代码段的赶脚)
- public (默认的修饰符)
- enum class (声明枚举类型)
- when (有返回值的表达式,支持任何对象)
- setOf(创建一个Set，会包含指定参数的对象，顺序无关)
- as XXX (显示类型转换) (lastChar as last:: 类似于alias(别名))
- while(cond) do{} while(cond) (同Java一致)
- in 1..10 (类似for(int i=1; i<=10;i++))
  亦可做区间判断 fun isLetter(c: Char) = c in 'A'..'Z' || c in 'a'..'z'
- for(i in 100 downTo 1 step 1 ) (lua: for i=100, 1, -1)downTo:target, step:步进值

- map操作
```
val map = TreeMap<Char, String>()
for (i in 'A'..'F') {
  binary = Integer.toBinaryString(i.toInt())
  map[i] = binary // 赋值
}

// 遍历
for((letter,binary) in map) {
    println("map key:$letter, value:$binary)
}
```
- List操作
```
val list = arrayListOf<Int>(1,2,3,4)
for((index,element) in list.withIndex()) {
    println("list index:$index, element:$element")
}
```

- in 区间判断
```
# 字符比较
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "I don't know!"
}


# 对象本身实现了java.lang.Comparable接口，ex(String)
println("kotlin" in "Java".."Scala")
println("kotlin" in setOf("Java", "Scala"))

```

- throw Exception("") (抛出异常不用添加new)
- 异常捕获
```
try {
  val line = "tomJ"
  return Integer.parseInt(line)
} catch(e:Exception) {
  println("Exception:$e")
} finally {
  println("wtf..")
}

#返回值
val result = try {
    Integer.parseInt(i)
} catch (e: NumberFormatException) {
    null
}

```

## note

- 同一package，无需import。跨package，自动import
- 无三元表达式