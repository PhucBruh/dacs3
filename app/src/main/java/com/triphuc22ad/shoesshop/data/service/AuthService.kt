package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.JwtResponse
import com.triphuc22ad.shoesshop.data.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/auth/signin")
    suspend fun login(@Body loginRequest: LoginRequest): Response<JwtResponse>
}