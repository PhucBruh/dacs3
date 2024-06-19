package com.triphuc22ad.shoesshop.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.service.AuthService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
    private val authService: AuthService
) : ViewModel() {

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

            is SignupEvent.ChangePhone -> {
                _state.value = _state.value.copy(
                    signUpInfo = _state.value.signUpInfo.copy(
                        phone = event.phone
                    )
                )
            }

            is SignupEvent.ChangeUsername -> {
                _state.value = _state.value.copy(
                    signUpInfo = _state.value.signUpInfo.copy(
                        username = event.username
                    )
                )
            }

            SignupEvent.SignUp -> {
            }
        }
    }

    fun signUp() {
        val signUpInfo = state.value.signUpInfo
        val confirmPassword = state.value.confirmPassword
        if (signUpInfo.email.isEmpty() ||
            signUpInfo.phone.isEmpty() ||
            signUpInfo.firstName.isEmpty() ||
            signUpInfo.lastName.isEmpty() ||
            signUpInfo.username.isEmpty() ||
            signUpInfo.password.isEmpty() ||
            confirmPassword.isEmpty()
        ) {
            appStateRepository.updateNotify("Please fill all fields")
        } else if (signUpInfo.password != confirmPassword) {
            appStateRepository.updateNotify("Password does not match")
        } else {
            viewModelScope.launch {
                val response = authService.register(
                    signUpInfo
                )
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        result.message?.let { appStateRepository.updateNotify(it) }
                    };
                } else {
                    appStateRepository.updateNotify("Something went wrong")
                }
            }
        }
    }
}