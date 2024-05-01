package com.triphuc22ad.shoesshop.presentation.orders.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.util.component.TopTitleBar

@Composable
fun TrackOrderScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),

        ) {
        TopTitleBar(name = "Track Order", modifier = Modifier.padding(top = 16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CardReviewItem(status = "In Delivery")



            Text(
                text = "Packet In Delivery",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        HorizontalDivider(Modifier.padding(top = 8.dp, bottom = 8.dp))

        Column {
            Text(
                text = "Order Status Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        }

    }

}

@Preview
@Composable
fun TrackOrderScreenPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            TrackOrderScreen()
        }
    }
}