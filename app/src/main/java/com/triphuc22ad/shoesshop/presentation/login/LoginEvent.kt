package com.triphuc22ad.shoesshop.presentation.login

sealed class LoginEvent {
    data class ChangeUserName(val userName: String) : LoginEvent()
    data class ChangePassword(val password: String) : LoginEvent()
    data object RememberMeToggle : LoginEvent()
    data object Login : LoginEvent()
    data object SignUp : LoginEvent()
}