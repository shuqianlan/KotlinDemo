package com.ilifesmart.kotlin_class

/*
* 通常来讲，类的所有声明都在括号内(主构造方法)
* 主构造方法:
* ** 1. 声明构造方法的参数
* ** 2. 定义参数初始化的属性
*
* constructor: 开始一个主构造函数的生命
* init: 引入一个初始化语句块，类创建时执行
* */
open class User constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }
}

/*无参*/
open class User1()

/*简版*/
open class User2(_nickname: String) {
    val nickname = _nickname
}

/*
*精简版
* 形参来替换User2中的声明
* val:相应的属性会用构造方法的参数来初始化
* */
open class User3(val nickname: String)

/*
* 默认值
* */
open class User4(val nickname: String = "wuzhenhgua")

/*
* 默认值
* */
open class User5(val int: Int, val nickname: String = "wuzhenhgua")

/*
* private(茕茕孑立)
* */
class User6 private constructor() {}

/*
* 类继承
* */
class TwitterUser(nickname: String): User3(nickname)

/*
* 类继承(缺省构造器)
* */
class WeiXinUser: User1()

// ======================================== 从构造方法



