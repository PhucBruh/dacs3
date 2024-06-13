package com.triphuc22ad.shoesshop.data.model

data class UserInfoResponse(
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val phone: String,
    val roles: List<String>,
    val username: String,
)