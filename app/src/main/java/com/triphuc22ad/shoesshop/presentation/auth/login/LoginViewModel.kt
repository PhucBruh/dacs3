package com.triphuc22ad.shoesshop.presentation.auth.login

import androidx.lifecycle.ViewModel
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
            is LoginEvent.ChangeUserName -> {
                _state.value = _state.value.copy(
                    loginInfo = _state.value.loginInfo.copy(
                        username = event.userName
                    )
                )
            }

            is LoginEvent.ChangePassword -> {
                _state.value = _state.value.copy(
                    loginInfo = _state.value.loginInfo.copy(
                        password = event.password
                    )
                )
            }

            is LoginEvent.Login -> {
                event.navigateToHome()
            }

            LoginEvent.RememberMeToggle -> TODO()
        }
    }
}