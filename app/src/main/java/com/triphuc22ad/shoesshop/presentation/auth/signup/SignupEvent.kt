package com.triphuc22ad.shoesshop.presentation.auth.signup

import com.triphuc22ad.shoesshop.presentation.auth.login.LoginEvent

sealed class SignupEvent {
    data class ChangeFirstName(val userName: String) : SignupEvent()
    data class ChangeLastName(val userName: String) : SignupEvent()
    data class ChangeEmail(val userName: String) : SignupEvent()
    data class ChangePassword(val password: String) : SignupEvent()
    data class ChangeConfirmPassword(val password: String) : SignupEvent()
    data object AcceptPolicyToggle : SignupEvent()
    data object SignUp : SignupEvent()
}