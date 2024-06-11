package com.triphuc22ad.shoesshop.data.repository

import com.triphuc22ad.shoesshop.data.service.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
}