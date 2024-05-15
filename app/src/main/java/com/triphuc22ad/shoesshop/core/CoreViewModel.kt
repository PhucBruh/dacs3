package com.triphuc22ad.shoesshop.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class CoreViewModel<STATE, EFFECT, ACTION, ENVIRONMENT>(
    initialState: STATE,
    private val environment: ENVIRONMENT,
) : ViewModel() {
    private val _effect = MutableStateFlow<EFFECT?>(null)
    val effect: StateFlow<EFFECT?> = _effect

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state

    abstract fun dispatch(action: ACTION)

    fun resetEffect() {
        _effect.value = null
    }

    protected fun setEffect(newEffect: EFFECT) {
        _effect.value = newEffect
    }

    protected fun setState(newState: STATE.() -> STATE) {
        _state.value = _state.value.newState()
    }

    private fun stateValue(): STATE {
        return _state.value
    }

}
