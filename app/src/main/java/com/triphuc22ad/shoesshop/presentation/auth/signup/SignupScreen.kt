package com.triphuc22ad.shoesshop.presentation.auth.signup;


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable;
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
import com.triphuc22ad.shoesshop.presentation.auth.login.components.CheckboxComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.ClickLoginTextComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.CustomTextField
import com.triphuc22ad.shoesshop.presentation.auth.login.components.DividerTextComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.HeadingTextComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.NormalTextComponent
import com.triphuc22ad.shoesshop.presentation.auth.login.components.SocialMediaLogin


@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            CustomTextField(
                value = state.signUpInfo.firstName,
                onValueChange = { viewModel.onEvent(SignupEvent.ChangeFirstName(it)) },
                icon = Icons.Default.AccountCircle,
                description = stringResource(id = R.string.first_name),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()

            )

            CustomTextField(
                value = state.signUpInfo.lastName,
                onValueChange = { viewModel.onEvent(SignupEvent.ChangeLastName(it)) },
                icon = Icons.Default.AccountCircle,
                description = stringResource(id = R.string.last_name),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )

            CustomTextField(
                value = state.signUpInfo.email,
                onValueChange = { viewModel.onEvent(SignupEvent.ChangeEmail(it)) },
                icon = Icons.Default.Email,
                description = "E-mail",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )

            CustomTextField(
                value = state.signUpInfo.password,
                onValueChange = { viewModel.onEvent(SignupEvent.ChangePassword(it)) },
                icon = Icons.Default.Lock,
                description = "Password",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )

            CheckboxComponent(
                checked = state.acceptPolicy,
                onToggle = { viewModel.onEvent(SignupEvent.AcceptPolicyToggle) }
            )

            ButtonComponent(
                modifier = Modifier.padding(top = 100.dp),
                value = "Register",
                onClick = { navigateToHome() })

            DividerTextComponent()

            ButtonComponent(
                modifier = Modifier.padding(top = 10.dp),
                value = "Login",
                onClick = { navigateToLogin() }

            )
        }
    }
}


@Preview
@Composable
fun Previeww() {
    SignupScreen(navigateToHome = {}, navigateToLogin = {})
}


