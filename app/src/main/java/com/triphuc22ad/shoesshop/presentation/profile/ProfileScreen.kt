package com.triphuc22ad.shoesshop.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.profile.components.ProfileOption
import com.triphuc22ad.shoesshop.presentation.profile.components.ProfileOptionContainer
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.util.component.TopTitleBar

@Composable
fun ProfileScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopTitleBar(name = "Profile")

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(140.dp)
                        .background(BgColor, CircleShape)
                        .clip(CircleShape)
                        .clickable { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.curry_6),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .background(BgColor, CircleShape)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .width(140.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.Black, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit, contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Phúc lỏ", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "+84 797 777 777", fontWeight = FontWeight.Medium)
            }

            HorizontalDivider(Modifier.padding(top = 8.dp))
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listOption) { option ->
                if (option.content != null) {
                    ProfileOptionContainer(
                        name = option.name,
                        icon = option.icon,
                        color = option.color,
                        onClick = { /*TODO*/ }) {
                        option.content.let { it() }
                    }
                } else {
                    ProfileOption(
                        name = option.name,
                        leftIcon = option.icon,
                        color = option.color,
                        description = option.description,
                        onClick = { /*TODO*/ })
                }
            }
        }
    }
}

val listOption = listOf(
    ProfileOption(
        name = "Edit Profile",
        icon = Icons.Outlined.Person,
    ),
    ProfileOption(
        name = "Address",
        icon = Icons.Outlined.LocationOn,
    ),
    ProfileOption(
        name = "Notification",
        icon = Icons.Outlined.Notifications,
    ),
    ProfileOption(
        name = "Payment",
        icon = Icons.Outlined.CreditCard,
    ),
    ProfileOption(
        name = "Security",
        icon = Icons.Outlined.Security,
    ),
    ProfileOption(
        name = "Language",
        icon = Icons.Outlined.Language,
        description = "English(US)"
    ),
    ProfileOption(
        name = "Dark Mode",
        icon = Icons.Outlined.RemoveRedEye,
        content = { Switch(checked = false, onCheckedChange = {}) }
    ),
    ProfileOption(
        name = "Help Center",
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
    ),
    ProfileOption(
        name = "Logout",
        icon = Icons.AutoMirrored.Filled.Logout,
        content = {},
        color = Color.Red.copy(0.6f)
    ),
)

data class ProfileOption(
    val name: String,
    val icon: ImageVector,
    val description: String? = null,
    val content: (@Composable() () -> Unit)? = null,
    val color: Color = Color.Black,
    val onClick: () -> Unit = {}
)

@Preview
@Composable
fun ProfileScreenPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            ProfileScreen()
        }
    }
}
