package com.triphuc22ad.shoesshop.presentation.product_detail.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun ExpandedText(
    text: String,
    expandedText: String,
    expandedTextButton: String,
    shrinkTextButton: String,
    modifier: Modifier = Modifier,
    softWrap: Boolean = true,
    textStyle: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    expandedTextStyle: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    expandedTextButtonStyle: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    shrinkTextButtonStyle: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
) {

    var isExpanded by remember { mutableStateOf(false) }


    val textHandler = "${if (isExpanded) expandedText else text} ${if (isExpanded) shrinkTextButton else expandedTextButton}"

    val annotatedString = buildAnnotatedString {
        withStyle(
            if (isExpanded) expandedTextStyle.toSpanStyle() else textStyle.toSpanStyle()
        ) {
            append(if (isExpanded) expandedText else text)
        }

        append("  ")

        withStyle(
            if (isExpanded) shrinkTextButtonStyle.toSpanStyle() else expandedTextButtonStyle.toSpanStyle()
        ) {
            append(if (isExpanded) shrinkTextButton else expandedTextButton)
        }

        addStringAnnotation(
            tag = "expand_shrink_text_button",
            annotation = if (isExpanded) shrinkTextButton else expandedTextButton,
            start = textHandler.indexOf(if (isExpanded) shrinkTextButton else expandedTextButton),
            end = textHandler.indexOf(if (isExpanded) shrinkTextButton else expandedTextButton) + if (isExpanded) expandedTextButton.length else shrinkTextButton.length
        )
    }

    ClickableText(
        text = annotatedString,
        softWrap = softWrap,
        modifier = modifier,
        onClick = {
            annotatedString
                .getStringAnnotations(
                    "expand_shrink_text_button",
                    it,
                    it
                )
                .firstOrNull()?.let { stringAnnotation ->
                    isExpanded = stringAnnotation.item == expandedTextButton
                }
        }
    )
}