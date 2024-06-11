package com.triphuc22ad.shoesshop.domain.model

import com.triphuc22ad.shoesshop.presentation.auth.signup.SignupEvent

data class User(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val role: String,
)