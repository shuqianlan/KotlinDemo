package com.ilifesmart.model

import java.util.*

class RectAngle(val height: Int, val width: Int) {
    val isSquare: Boolean
    get() { // 属性访问器的自定义实现
        return height == width
    }

    val person: PersonBean2
    get() {
        return PersonBean2("name", 20, false)
    }

    fun creatRandomRectangle(): RectAngle {
        val random = Random()
        return RectAngle(random.nextInt(), random.nextInt())
    }
}