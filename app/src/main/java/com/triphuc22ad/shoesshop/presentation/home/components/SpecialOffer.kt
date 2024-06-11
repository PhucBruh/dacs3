package com.triphuc22ad.shoesshop.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.AppTheme

@Composable
fun SpecialOfferBanner(
    specialOffer: SpecialOffer,
    onClick: () -> Unit = {},
    shadow: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(shape = RoundedCornerShape(if (shadow) 32.dp else 36.dp), elevation = 4.dp)
            .background(BgColor, RoundedCornerShape(36.dp))
            .clip(RoundedCornerShape(36.dp))
            .clickable { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 32.dp)
                .padding(start = 24.dp)
        ) {
            Text(
                text = "${specialOffer.value.toInt()}% off",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            Text(
                text = specialOffer.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = specialOffer.description,
                fontSize = 12.sp
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = specialOffer.img,
                contentDescription = null,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun SpecialOfferPreview() {
    AppTheme {
        Surface {
            SpecialOfferBanner(
                modifier = Modifier.padding(
                    vertical = 10.dp, horizontal = 16.dp
                ),
                specialOffer = SpecialOffer(
                    value = 25.0,
                    name = "Today's Special",
                    description = "Get discount for every order, only valid for today",
                    img = "",
                    active = true,
                    productId = 1
                ),
            )
        }
    }
}

