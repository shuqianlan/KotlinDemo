# kotlin语法

## 静态类型的编程语言

未指定类型在初次初始化的时候确定类型，后期不可再度更改

- : Int(类型声明)
- val age:Int? = null //(可空类型)
- ?: 0 （Elvis运算符会返回0）
- val 变量声明符
- max(a:Int, b:Int): Int (返回类型: Int)
- val(类似const,仅能一次初始化)， var(非const)
- ${a+b} (将a+b结果转输出，可用于复杂表达式) (代码段的赶脚)
- public (默认的修饰符)
- enum class (声明枚举类型)
- when (有返回值的表达式,支持任何对象)
- setOf(创建一个Set，会包含指定参数的对象，顺序无关)
- as XXX (显示类型转换)
- while(cond) do{} while(cond) (同Java一致)

## note

- 同一package，无需import。跨package，自动import
- 无三元表达式