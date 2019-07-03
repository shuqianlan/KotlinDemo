package com.ilifesmart.kotlin_class

class DelegateCollections<T>: Collection<T> {

    override val size: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun contains(element: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): Iterator<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

/*
* 委托类
* 好处:避免基类改变此处需要改变颇多的情况
* by: 将接口的实现委托到另一个对象
* */
class DelegateCollections2<T> (
    innerList: Collection<T> = ArrayList<T>()
):Collection<T> by innerList {}