package com.ilifesmart.kotlin

import com.ilifesmart.java.DataParser
import com.ilifesmart.kotlindemo.MainActivity

class PersonParser: DataParser<MainActivity.CompanyPerson> {
    override fun parseData(
        input: String?,
        output: MutableList<MainActivity.CompanyPerson>,
        errors: MutableList<String>?
    ) {

    }
}