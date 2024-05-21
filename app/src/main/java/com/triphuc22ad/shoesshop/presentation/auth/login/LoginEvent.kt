package com.triphuc22ad.shoesshop.presentation.auth.login

sealed class LoginEvent {
    data class ChangeUserName(val userName: String) : LoginEvent()
    data class ChangePassword(val password: String) : LoginEvent()
    data object RememberMeToggle : LoginEvent()
    data class Login(val navigateToHome: () -> Unit) : LoginEvent()
}