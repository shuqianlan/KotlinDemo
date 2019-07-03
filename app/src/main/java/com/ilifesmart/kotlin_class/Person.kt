package com.ilifesmart.kotlin_class


class Person(val name:String, val money: Double=0.0) {
    companion object :JSONFactory<Person> {
        override fun fromJSON(jsonText: String): Person {
            return Person("name")
        }
    }
}

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

// ------------------------ 伴生对象的扩展函数

class Person2(val firstName: String, val lastName: String) {
    companion object {} // 空伴生对象
}

// 扩展函数
fun Person2.Companion.fromJSON(json:String): Person2 {
    return Person2("Tom", "Jackson")
}