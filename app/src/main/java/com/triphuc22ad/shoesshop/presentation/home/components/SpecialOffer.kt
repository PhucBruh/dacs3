package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme

@Composable
fun SpecialOffer(
    shadow: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(shape = RoundedCornerShape(if (shadow) 32.dp else 36.dp), elevation = 4.dp)
            .background(BgColor, RoundedCornerShape(36.dp))
            .clip(RoundedCornerShape(36.dp))
            .clickable { }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 32.dp)
                .padding(start = 24.dp)
        ) {
            Text(
                text = "25%",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            Text(
                text = "Today's Special",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "Get discount for every order, only valid for today",
                fontSize = 12.sp
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.curry_6),
                contentDescription = null,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun SpecialOfferPreview() {
    Dacs3shoesshopandroidTheme {
        Surface {
            SpecialOffer(modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp))
        }
    }
}

