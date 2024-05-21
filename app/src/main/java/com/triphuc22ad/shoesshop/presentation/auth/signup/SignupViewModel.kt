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
            SignupEvent.AcceptPolicyToggle -> {
                _state.value = _state.value.copy(
                    acceptPolicy = !state.value.acceptPolicy
                )
            }

            is SignupEvent.ChangeEmail -> {
                _state.value = _state.value.copy(
                    signUpInfo = _state.value.signUpInfo.copy(
                        email = event.email
                    )
                )
            }

            is SignupEvent.ChangeFirstName -> {
                _state.value = _state.value.copy(
                    signUpInfo = _state.value.signUpInfo.copy(
                        firstName = event.firstName
                    )
                )
            }

            is SignupEvent.ChangeLastName -> {
                _state.value = _state.value.copy(
                    signUpInfo = _state.value.signUpInfo.copy(
                        lastName = event.lastName
                    )
                )
            }

            is SignupEvent.ChangePassword -> {
                _state.value = _state.value.copy(
                    signUpInfo = _state.value.signUpInfo.copy(
                        password = event.password
                    )
                )
            }

            is SignupEvent.ChangeConfirmPassword -> {
                _state.value = _state.value.copy(
                    confirmPassword = event.confirmPassword
                )
            }

            SignupEvent.SignUp -> {

            }
        }
    }
}