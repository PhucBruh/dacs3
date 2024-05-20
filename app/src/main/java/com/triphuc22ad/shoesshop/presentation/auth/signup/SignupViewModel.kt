package com.triphuc22ad.shoesshop.presentation.auth.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignupUiState())
    val state: StateFlow<SignupUiState> = _state.asStateFlow()

    fun onEvent(event: SignupEvent) {
        when (event) {
            SignupEvent.AcceptPolicyToggle -> TODO()
            is SignupEvent.ChangeConfirmPassword -> TODO()
            is SignupEvent.ChangeEmail -> TODO()
            is SignupEvent.ChangeFirstName -> TODO()
            is SignupEvent.ChangeLastName -> TODO()
            is SignupEvent.ChangePassword -> TODO()
            SignupEvent.SignUp -> TODO()
        }
    }
}