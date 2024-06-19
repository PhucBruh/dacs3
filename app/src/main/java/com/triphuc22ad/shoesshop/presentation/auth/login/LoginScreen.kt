package com.triphuc22ad.shoesshop.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.auth.login.components.ButtonComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.CustomTextField
import com.triphuc22ad.shoesshop.presentation.auth.login.components.DividerTextComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.HeadingTextComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.NormalTextComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.UnderlinedTextComponent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NormalTextComponent(value = stringResource(id = R.string.hello))

            HeadingTextComponent(value = stringResource(id = R.string.login_account))

            CustomTextField(
                value = state.loginInfo.username,
                onValueChange = { viewModel.onEvent(LoginEvent.ChangeUserName(it)) },
                icon = Icons.Default.Email,
                description = "Email",
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()

            )

            CustomTextField(
                value = state.loginInfo.password,
                onValueChange = { viewModel.onEvent(LoginEvent.ChangePassword(it)) },
                icon = Icons.Default.Lock,
                description = "Password",
                password = true,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 20.dp)
                    .fillMaxWidth()
            )

            UnderlinedTextComponent(value = "Forgot your password?")

            ButtonComponent(
                modifier = Modifier.padding(top = 200.dp),
                value = "Login",
                onClick = { viewModel.onEvent(LoginEvent.Login(navigateToHome)) })

            DividerTextComponent()

            ButtonComponent(
                modifier = Modifier.padding(top = 10.dp),
                value = "Register",
                onClick = { navigateToRegister() }
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    LoginScreen(navigateToHome = {}, navigateToRegister = {})
}