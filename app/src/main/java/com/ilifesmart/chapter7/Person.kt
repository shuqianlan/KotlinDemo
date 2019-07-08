package com.ilifesmart.chapter7

class Person (
    val firstName: String, val lastName: String
): Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, Person::firstName, Person::lastName)
    }
}