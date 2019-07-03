package com.ilifesmart.kotlin_class

/*
* name:客户名字
* postalCode:邮编
* */
class Client (val name:String, val postalCode: Int) {
    override fun toString(): String {
        return "Client(name=$name, postalCode=$postalCode)"
    }

    // ANy:java中Object的模拟
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) {
            return false
        }

        return (other.name.equals(name) && other.postalCode==postalCode)
    }

    override fun hashCode(): Int {
        return name.hashCode() * 31 + postalCode
    }

    fun copy(name: String=this.name, postalCode: Int=this.postalCode):Client {
        return Client(name, postalCode)
    }
}

/*
* data: 默认已经按照主构造方法实现了toString/equals/hashCode
* */
data class Client2(val name:String, val postalCode: Int) {
}