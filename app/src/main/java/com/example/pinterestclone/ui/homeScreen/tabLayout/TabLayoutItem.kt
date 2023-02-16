package com.example.pinterestclone.ui.homeScreen.tabLayout

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinterestclone.ui.theme.PinterestCloneTheme
import com.example.pinterestclone.ui.theme.TabLayoutDividerHeight
import com.example.pinterestclone.ui.theme.TabLayoutItemPadding

@Composable
fun TabLayoutItem(category: Category) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(TabLayoutItemPadding)
            .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = category.category,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(horizontal = TabLayoutItemPadding)
        )
        Divider(
            modifier = Modifier
                .padding(top = TabLayoutItemPadding)
                .height(TabLayoutDividerHeight)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colors.onSurface)
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabLayoutItemPreview() {
    PinterestCloneTheme {
        TabLayoutItem(category = Category("All"))
    }
}