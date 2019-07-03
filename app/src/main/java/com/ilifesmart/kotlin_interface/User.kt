package com.ilifesmart.kotlin_interface

interface User {
    val nickname: String
    val emailToken:String // 可继承
    get() = nickname+"@qq.com"
}

/*接口实现，参数声明*/
class PrivateUser(override val nickname: String): User

/*接口实现，getter声明*/
class SubscribeUser(val email:String): User {
    override val nickname: String
        get() = email.substringBefore("@")
}

/*接口实现，间接调用声明*/
class FacebookUser(val accountId: Int): User {
    override val nickname = getFaceBookName(accountId)

    fun getFaceBookName(accountId: Int): String = "FaceBook"+accountId
}

class AddressUser(val name: String) {
    var address: String = "unspecified"
    set(value:String) {
        println("Address changed for $name")
        field = value
    }

//    get() = field // 默认实现

    override fun toString(): String {
        return "name:"+name+", address:"+address
    }
}