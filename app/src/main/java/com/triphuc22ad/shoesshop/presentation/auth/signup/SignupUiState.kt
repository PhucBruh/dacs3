package com.triphuc22ad.shoesshop.presentation.auth.signup

import com.triphuc22ad.shoesshop.domain.model.SignupInfo

data class SignupUiState(
    val signUpInfo: SignupInfo = SignupInfo(),
    val confirmPassword: String = "",
    val acceptPolicy: Boolean = false,
    val message: String = "",
)