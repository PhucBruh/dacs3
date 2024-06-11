package com.triphuc22ad.shoesshop.domain.model

data class LoginInfo(
    val username: String = "",
    val password: String = "",
)

data class SignupInfo(
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val email: String = "",
    val phone: String = "",
)