package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.JwtResponse
import com.triphuc22ad.shoesshop.data.model.LoginRequest
import com.triphuc22ad.shoesshop.domain.model.SignupInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/auth/signin")
    suspend fun login(@Body loginRequest: LoginRequest): Response<JwtResponse>

    @POST("/api/auth/signup")
    suspend fun register(@Body registerRequest: SignupInfo): Response<ApiResponse>
}