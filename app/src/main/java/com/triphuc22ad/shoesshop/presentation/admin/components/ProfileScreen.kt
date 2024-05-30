package com.triphuc22ad.shoesshop.presentation.admin.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


//@Composable
//fun ProfileScreen() {
//    Text("Profile Screen")
//}

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.ui.theme.BgColor


data class Account(val Name: String,
                   val role: String,
                   val imageResourceId: Int
)


val account = listOf(
    Account("Name 1", "Admin", R.drawable.curry_6),
    Account("Name 2", "User", R.drawable.curry_6),
    Account("Name 3", "User", R.drawable.curry_6)
)


@Composable
fun ProfileScreen(account: List<Account>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(account) { account ->
            AdminOrderCard(account = account, onEditClick = {

            })
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun AdminOrderCard(account: Account, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                Modifier
                    .padding(vertical = 10.dp)
                    .padding(start = 5.dp)
                    .shadow(2.dp, RoundedCornerShape(32.dp))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .background(BgColor, RoundedCornerShape(32.dp))
                ) {
                    Image(
                        painter = painterResource(id = account.imageResourceId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = account.Name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Role: ${account.role}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black,
                    modifier = Modifier.clickable { onEditClick() }
                )

                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black,
                    modifier = Modifier.clickable { }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(account = account)
}

