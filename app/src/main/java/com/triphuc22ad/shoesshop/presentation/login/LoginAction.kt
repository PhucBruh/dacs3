package com.triphuc22ad.shoesshop.presentation.login

sealed class LoginAction {
    data class ChangeUserName(val userName: String) : LoginAction()
    data class ChangePassword(val password: String) : LoginAction()
    data object RememberMeToggle : LoginAction()
    data object Login : LoginAction()
    data object SignUp : LoginAction()
}