package com.triphuc22ad.shoesshop.presentation.login.components

import android.util.Log
import android.view.textclassifier.TextSelection
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.ui.theme.GrayColor
import com.triphuc22ad.shoesshop.ui.theme.Primary
import com.triphuc22ad.shoesshop.ui.theme.Secondary
import com.triphuc22ad.shoesshop.ui.theme.TextColor

@Composable
fun NormalTextComponent(value: String) {
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
        .background(color = Color.White),
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = "")
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = description)
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
    )
}

@Composable
fun CheckboxComponent(value: String) {
    Row(horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)


    ) {
        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = checkedState.value xor true
            })
        Text(text = "By continuing you accept our Privacy Policy and Term of Use",
            color = Color.Gray
        )
    }
}

@Composable
fun ButtonComponent(modifier: Modifier = Modifier
    .fillMaxWidth()
    .heightIn(48.dp), value: String) {
    Button(
        onClick = { },
        modifier = modifier,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DividerTextComponent() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        color = GrayColor,
        thickness = 1.dp)

        Text(text = "or", fontSize = 16.sp, color = TextColor)

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = GrayColor,
            thickness = 1.dp)
    }
}

@Composable
fun ClickLoginTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Already have an account? "
    val logintext = "Login"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = TextColor, fontWeight = FontWeight.Bold, fontSize = 15.sp)){
            pushStringAnnotation(tag = logintext, annotation = logintext)
            append(logintext)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also {span ->
                Log.d("ClickLoginTextComponent", "${span.item}")
                if (span.item == logintext){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun ClickRegisterTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Don't have an account yet? "
    val registerntext = "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = TextColor, fontWeight = FontWeight.Bold, fontSize = 15.sp)){
            pushStringAnnotation(tag = registerntext, annotation = registerntext)
            append(registerntext)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also {span ->
                Log.d("ClickLoginTextComponent", "${span.item}")
                if (span.item == registerntext){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun SocialMediaLogin(modifier: Modifier,
                     @DrawableRes icon: Int,
                     text : String,
                     onClick: () -> Unit)
{
    Row(modifier = Modifier
        .clip(RoundedCornerShape(4.dp))
        .clickable { onClick }
        .height(90.dp)
        .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,)
    {
        Image(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text, style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B)))
    }
}

@Composable
fun UnderlinedTextComponent(value: String) {
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),color = Color.Gray,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}