package com.triphuc22ad.shoesshop.presentation.signup;


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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.login.components.ButtonComponent
import com.triphuc22ad.shoesshop.presentation.login.components.CheckboxComponent
import com.triphuc22ad.shoesshop.presentation.login.components.ClickLoginTextComponent
import com.triphuc22ad.shoesshop.presentation.login.components.CustomTextField
import com.triphuc22ad.shoesshop.presentation.login.components.DividerTextComponent
import com.triphuc22ad.shoesshop.presentation.login.components.HeadingTextComponent
import com.triphuc22ad.shoesshop.presentation.login.components.NormalTextComponent
import com.triphuc22ad.shoesshop.presentation.login.components.SocialMediaLogin


@Composable
fun SingUp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            CustomTextField(
                value = "",
                onValueChange = {},
                icon = Icons.Default.AccountCircle,
                description = stringResource(id = R.string.first_name),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()

            )

            CustomTextField(
                value = "",
                onValueChange = {},
                icon = Icons.Default.AccountCircle,
                description = stringResource(id = R.string.last_name),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )

            CustomTextField(
                value = "",
                onValueChange = {},
                icon = Icons.Default.Email,
                description = "E-mail",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )

            CustomTextField(
                value = "",
                onValueChange = {},
                icon = Icons.Default.Lock,
                description = "Password",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )

            CheckboxComponent(value = "")

            ButtonComponent(modifier = Modifier.padding(top = 100.dp),value = "Register")

            DividerTextComponent()

            Row(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                )
            {
                SocialMediaLogin(icon = R.drawable.google,
                    text = "Google",
                    modifier = Modifier.weight(1f)){
                }

                Spacer(modifier = Modifier.width(50.dp))

                SocialMediaLogin(icon = R.drawable.facebook,
                    text = "Facebook",
                    modifier = Modifier.weight(1f)){
                }
            }

            ClickLoginTextComponent(onTextSelected = {})


        }

    }
}


@Preview
@Composable
fun Previeww() {
    SingUp()
}


