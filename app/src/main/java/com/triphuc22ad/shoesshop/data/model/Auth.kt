package com.triphuc22ad.shoesshop.data.model

data class LoginRequest(
    val password: String,
    val usernameOrEmail: String,
)

data class RegisterRequest(
    val email: String,
    val phone: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val username: String,
)

data class JwtResponse(
    val accessToken: String,
    val tokenType: String
)