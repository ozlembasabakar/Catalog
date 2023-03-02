package com.example.pinterestclone.tabs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinterestclone.ui.theme.*

@Composable
fun TabItem(category: String) {
    Column(
        modifier = Modifier
            .height(TabLayoutItemHeight)
            .background(MaterialTheme.colorScheme.surface)
            .padding(TabLayoutItemPadding)
            .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = category,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(horizontal = TabLayoutItemPadding)
                .weight(TabItemWeight)
                .wrapContentHeight(CenterVertically)
                .wrapContentWidth(),
        )
        Box(
            modifier = Modifier
                .height(TabLayoutDividerHeight)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.onSurface),
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabItemPreview() {
    PinterestCloneTheme {
        TabItem(category = "Category")
    }
}