package com.ilifesmart.kotlin

class Button2: View2 {
    override fun getCurrentState(): State {
        println("getCurrentState")
        return ButtonState()
    }

    override fun restoreState(state: State) {
        println("restoreState--")
    }

    // 内部类(保存引用)持有外部类引用及访问方法
    inner class ButtonState2: State {
        fun getButton2(): Button2 = this@Button2
    }

    // 内部嵌套类(不保存引用)
    class ButtonState: State {
    }
}