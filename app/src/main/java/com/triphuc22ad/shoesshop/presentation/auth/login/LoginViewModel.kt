package com.triphuc22ad.shoesshop.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.TokenManager
import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.JwtResponse
import com.triphuc22ad.shoesshop.data.model.LoginRequest
import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.data.service.AuthService
import com.triphuc22ad.shoesshop.data.service.UserService
import com.triphuc22ad.shoesshop.presentation.app.AppStateRepository
import com.triphuc22ad.shoesshop.presentation.app.AppViewModel
import com.triphuc22ad.shoesshop.presentation.auth.login.components.LoginUiSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager,
    private val userService: UserService,
    private val appStateRepository: AppStateRepository,
) : ViewModel() {
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
//                event.navigateToHome()
                viewModelScope.launch {
                    val response = authService.login(
                        LoginRequest(
                            usernameOrEmail = _state.value.loginInfo.username,
                            password = _state.value.loginInfo.password
                        )
                    )
                    if (response.isSuccessful) {
                        response.body()?.accessToken?.let { tokenManager.saveToken(it) }
                        val response = userService.getMyInfo()

                        if (response.success) {
//                            val jwtResponse = response.data as UserInfoResponse
                            if (response.data is UserInfoResponse) {
                                appStateRepository.updateUserInfo(response.data as UserInfoResponse)
                                event.navigateToHome()
                            }
                        }
                    } else if (response.code() == 400) {
                        appStateRepository.updateNotify("User name or password not valid")
                    }
                }
            }

            LoginEvent.RememberMeToggle -> TODO()
        }
    }

    fun isValidUsername(username: String): Boolean {
        return false
    }

    fun isValidPassword(password: String): Boolean {
        return false
    }
}