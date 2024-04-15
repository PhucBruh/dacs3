package com.triphuc22ad.shoesshop.presentation.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.product_detail.components.ExpandedText
import com.triphuc22ad.shoesshop.presentation.product_detail.components.SizeComponent
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.GrayColor
import com.triphuc22ad.shoesshop.ui.theme.TextColor
import java.time.format.TextStyle

@Composable
fun ProductDetailScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(28.dp)) {
        Column(modifier = Modifier
                .background(Color.White)
                ){
            TitleHeader(name = "Air Jodan 3 retro",
                onSeeAllClick = {},
                modifier = Modifier)


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = "8,374 sold",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .background(BgColor, RoundedCornerShape(8.dp))
                        .padding(vertical = 2.dp, horizontal = 6.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "4.9 (6.573reviews)",)
                Spacer(modifier = Modifier.width(8.dp))
                VerticalDivider(modifier = Modifier.height(14.dp), color = Color.Black)
            }

            DividerTextComponent()

            Text( text = "Description",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 15.dp),
            )

            ExpandedText(
                text = stringResource(id = R.string.nofull),
                expandedText = stringResource(id = R.string.full),
                expandedTextButton = " view more..",
                shrinkTextButton = " less",
                textStyle = androidx.compose.ui.text.TextStyle(),
                expandedTextStyle = androidx.compose.ui.text.TextStyle(),
                expandedTextButtonStyle = androidx.compose.ui.text.TextStyle( fontWeight = FontWeight.Bold),
                shrinkTextButtonStyle = androidx.compose.ui.text.TextStyle( fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {

                Column(
                ) {
                    Text(
                        text = "Size",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                         )

                    Row() {
                        SizeComponent(text = "40", onClick = {})
                        Spacer(modifier = Modifier.width(4.dp))
                        SizeComponent(text = "41", onClick = {})
                        Spacer(modifier = Modifier.width(4.dp))
                        SizeComponent(text = "42", onClick = {})
                    }

                }

                Column() {
                    Text(
                        text = "Color",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }

            }

        }

        }
    }


@Composable
fun TitleHeader(
    name: String,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            tint = Color.Black,
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .clickable { onSeeAllClick() }
        )
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
            thickness = 0.5.dp)
    }
}

@Preview
@Composable
fun PreviewProductDetailScreen(){
    ProductDetailScreen()
}