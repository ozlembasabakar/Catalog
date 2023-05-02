package com.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.theme.*

@Composable
fun Button(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    onClick: (String) -> Unit,
) {

    Column(
        modifier = modifier
            .height(ButtonHeight)
            .background(MaterialTheme.colorScheme.surface)
            .width(IntrinsicSize.Max)
            .clickable(
                onClick = {
                    onClick(
                        text
                    )
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(
                    vertical = ButtonVerticalPadding,
                    horizontal = ButtonHorizontalPadding
                )
                .wrapContentHeight(CenterVertically)
                .wrapContentWidth(),
        )
        Box(
            modifier = Modifier
                .height(ButtonDividerHeight)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .background(
                    when {
                        isSelected -> MaterialTheme.colorScheme.onSurface
                        else -> MaterialTheme.colorScheme.surface
                    }
                ),
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabItemPreview_isSelected() {
    PinterestCloneTheme {
        Button(
            modifier = Modifier,
            text = "Category",
            onClick = {},
            isSelected = true
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabItemPreview() {
    PinterestCloneTheme {
        Button(
            modifier = Modifier,
            text = "Category",
            onClick = {},
            isSelected = false
        )
    }
}