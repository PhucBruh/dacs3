package com.triphuc22ad.shoesshop.presentation.auth.signup

sealed class SignupEvent {
    data class ChangeFirstName(val firstName: String) : SignupEvent()
    data class ChangeLastName(val lastName: String) : SignupEvent()
    data class ChangeEmail(val email: String) : SignupEvent()
    data class ChangePassword(val password: String) : SignupEvent()
    data class ChangeConfirmPassword(val confirmPassword: String) : SignupEvent()
    data object AcceptPolicyToggle : SignupEvent()
    data object SignUp : SignupEvent()
}