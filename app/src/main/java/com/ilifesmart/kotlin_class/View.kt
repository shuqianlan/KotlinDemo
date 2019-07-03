package com.ilifesmart.kotlin_class

import android.content.Context
import android.util.AttributeSet

/*
* 可继承的View基类
* */
open class View {
    constructor(ctx: Context) {

    }

    constructor(ctx: Context, attr:AttributeSet?) {

    }
}

/*
* 继承View的Button类
* */
class Button: View, clickable {
    constructor(ctx: Context): this(ctx, null) {

    }

    constructor(ctx: Context, attr: AttributeSet?): super(ctx, attr) {

    }

    override var click: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
}

/*
* interface:
* */

interface clickable {
    var click: Boolean
}