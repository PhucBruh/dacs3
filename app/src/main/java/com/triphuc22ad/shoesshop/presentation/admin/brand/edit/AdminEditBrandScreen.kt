package com.triphuc22ad.shoesshop.presentation.admin.brand.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triphuc22ad.shoesshop.presentation.components.ImagePreview
import com.triphuc22ad.shoesshop.presentation.components.TopTitleBar
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun AdminEditBrandScreen(
    adminEditBrandViewModel: AdminEditBrandViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
) {
    val state by adminEditBrandViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        TopTitleBar(
            name = "Edit brand", modifier = Modifier.padding(top = 16.dp),
            onLeftAction = navigateBack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            item {
                TextField(
                    value = state.brandToEdit.id.toString(),
                    onValueChange = {},
                    enabled = false,
                    singleLine = true,
                    label = { Text("Id") },
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                TextField(
                    value = state.brandToEdit.name,
                    onValueChange = {
                        adminEditBrandViewModel.onEvent(AdminEditBrandEvent.ChangeName(it))
                    },
                    singleLine = true,
                    label = { Text("Name") },
                    modifier = Modifier
                        //
                        .fillMaxWidth() // Adjust padding if needed
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = state.brandToEdit.img_url,
                        onValueChange = {
                            adminEditBrandViewModel.onEvent(AdminEditBrandEvent.ChangeImage(it))
                        },
                        label = { Text("Img") },
                        modifier = Modifier
                            //
                            .weight(0.7f)
                            .padding(end = 8.dp)
                    )
                    Button(onClick = { adminEditBrandViewModel.onEvent(AdminEditBrandEvent.CheckImg) }) {
                        Text(text = "Check")
                    }
                }
            }

            item {
                ImagePreview(
                    model = state.brandToEdit.img_url,
                    description = state.productDetailMainImgPreview.toString(),
                    onClick = { adminEditBrandViewModel.onEvent(AdminEditBrandEvent.DeleteCheckImg) })
            }
        }
    }
}

@Preview
@Composable
fun AdminAddBrandScreenPreview() {
    AppTheme {
        Surface {
            AdminEditBrandScreen()
        }
    }
}