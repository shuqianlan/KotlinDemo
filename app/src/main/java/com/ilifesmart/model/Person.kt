package com.ilifesmart.model

data class Person (val name:String? = null, val age:Int? = null) {
    fun main() {
        val persons = listOf(Person("Alice"), Person("Tom", 26));
        val oldest = persons.maxBy { it.age ?: 0 }
        println("The oldest is: $oldest")
        println("persons: $persons")

    }


}