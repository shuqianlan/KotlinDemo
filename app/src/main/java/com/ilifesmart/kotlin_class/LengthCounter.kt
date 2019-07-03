package com.ilifesmart.kotlin_class

/*
*
* 可变属性setter设定为私有
* */
class LengthCounter {
    var counter:Int = 0
    private set

    fun addWord(word: String) {
        counter += word.length
    }
}