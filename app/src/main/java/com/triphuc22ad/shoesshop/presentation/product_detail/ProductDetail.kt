package com.triphuc22ad.shoesshop.presentation.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triphuc22ad.shoesshop.R
import com.triphuc22ad.shoesshop.presentation.home.HomeScreen
import com.triphuc22ad.shoesshop.presentation.product_detail.components.ColorCircleButton
import com.triphuc22ad.shoesshop.presentation.product_detail.components.ExpandedText
import com.triphuc22ad.shoesshop.presentation.product_detail.components.SizeComponent
import com.triphuc22ad.shoesshop.presentation.product_detail.components.TextCircleButton
import com.triphuc22ad.shoesshop.ui.theme.BgColor
import com.triphuc22ad.shoesshop.ui.theme.Dacs3shoesshopandroidTheme
import com.triphuc22ad.shoesshop.ui.theme.GrayColor
import com.triphuc22ad.shoesshop.ui.theme.Primary
import com.triphuc22ad.shoesshop.ui.theme.Secondary

@Composable
fun ProductDetailScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Back(onBack = { })

                Image(
                    painter = painterResource(id = R.drawable.curry_6),
                    contentDescription = "giay",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp, bottom = 100.dp)
                        .size(width = 100.dp, height = 200.dp)
                )
            }

            TitleHeader(
                name = "Air Jodan 3 retro",
                onSeeAllClick = {},
                modifier = Modifier
            )

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
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "4.9 (6.573reviews)")
                Spacer(modifier = Modifier.width(8.dp))
                VerticalDivider(modifier = Modifier.height(14.dp), color = Color.Black)
            }

            DividerTextComponent()

            Text(
                text = "Description",
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
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                ) {
                    Text(
                        text = "Size",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(4) {
                            TextCircleButton(text = "40", onClick = {})
                        }
                    }
                }

                Column(
                ) {
                    Text(
                        text = "Color",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(4) {
                            ColorCircleButton(color = Color.Cyan , onClick = {})
                        }
                    }
                }
            }

            QuantitySelector(initialQuantity = 0,
                onQuantityChange = { newQuantity ->
                    println("$newQuantity")
                })

            DividerTextComponent()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(start = 20.dp)) {
                    Text(text = "Total price",
                        fontSize = 15.sp
                    )
                    Text(
                        text = "$105.00",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                }

                ButtonComponent(value = "Add to Cart")

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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 0.5.dp
        )
    }
}

@Composable
fun QuantitySelector(initialQuantity: Int, onQuantityChange: (Int) -> Unit) {
    var quantity by remember { mutableStateOf(initialQuantity) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Quantity",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(end = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
        ) {
            IconButton(
                onClick = { if (quantity > 0) quantity-- },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(Icons.Default.Remove, contentDescription = "Remove", tint = Color.White)
            }

            Text(
                text = "$quantity",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            IconButton(
                onClick = { quantity++ },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    }
}


@Composable
fun ButtonComponent(modifier: Modifier = Modifier
    .heightIn(48.dp), value: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .padding()
                .heightIn(48.dp)
                .background(
                    Color.Black,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center,
        ){
            Row(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddShoppingCart,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                )

                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun Back(onBack: () -> Unit) {
    IconButton(onClick = { onBack() }, modifier = Modifier.size(28.dp)) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 5.dp)
        )
    }
}


@Preview
@Composable
fun PreviewProductDetailScreen() {
    Dacs3shoesshopandroidTheme {
        Surface {
            ProductDetailScreen()
        }
    }

}