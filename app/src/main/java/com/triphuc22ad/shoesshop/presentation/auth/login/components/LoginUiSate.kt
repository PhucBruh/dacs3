package com.triphuc22ad.shoesshop.presentation.auth.login.components

import com.triphuc22ad.shoesshop.domain.model.LoginInfo
import com.triphuc22ad.shoesshop.presentation.auth.login.LoginEvent

data class LoginUiSate(
    val loginInfo: LoginInfo = LoginInfo(),
    val message: String = "",
)