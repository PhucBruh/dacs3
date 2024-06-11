package com.triphuc22ad.shoesshop.data.model

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
//@Keep
data class UserInfoResponse(
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val phone: String,
    val roles: List<String>,
    val username: String,
)