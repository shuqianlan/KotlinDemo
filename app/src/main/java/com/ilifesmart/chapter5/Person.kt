package com.ilifesmart.chapter5

import com.ilifesmart.jkid.JsonExclude
import com.ilifesmart.jkid.JsonName

data class Person2(
    @JsonName("_name") val name: String,
    @JsonExclude val age: Int=0
) {
    fun isAdult() = age >= 21
}