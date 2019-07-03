package com.ilifesmart.chapter5

data class Person2(val name: String, val age: Int) {
    fun isAdult() = age >= 21
}