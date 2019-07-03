package com.ilifesmart.kotlin

import java.io.Serializable

interface State: Serializable

interface View2 {
    fun getCurrentState(): State
    fun restoreState(state: State)
}