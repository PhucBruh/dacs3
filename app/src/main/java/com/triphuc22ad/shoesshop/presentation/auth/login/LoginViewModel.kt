package com.triphuc22ad.shoesshop.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.domain.model.LoginInfo
import com.triphuc22ad.shoesshop.presentation.auth.login.components.LoginUiSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(LoginUiSate())
    val state: StateFlow<LoginUiSate> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ChangePassword -> TODO()
            is LoginEvent.ChangeUserName -> TODO()
            LoginEvent.Login -> TODO()
            LoginEvent.RememberMeToggle -> TODO()
            LoginEvent.SignUp -> TODO()
        }
    }
}

private val _state = MutableStateFlow(LoginUiSate())
val state: StateFlow<LoginUiSate> = _state.asStateFlow()

fun onEvent(event: LoginEvent) {
    when (event) {
        is LoginEvent.ChangePassword -> TODO()
        is LoginEvent.ChangeUserName -> TODO()
        LoginEvent.Login -> TODO()
        LoginEvent.RememberMeToggle -> TODO()
        LoginEvent.SignUp -> TODO()
    }
}
