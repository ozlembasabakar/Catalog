package com.example.pinterestclone.ui.homeScreen.tabLayout

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinterestclone.ui.theme.PinterestCloneTheme
import com.example.pinterestclone.ui.theme.TabLayoutPadding
import com.example.pinterestclone.ui.theme.TabLayoutSpacerSize

@Composable
fun TabLayout(modifier: Modifier) {
    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(TabLayoutPadding)
    ) {
        items(15) {
            TabLayoutItem(category = Category().category)
            Spacer(modifier = Modifier.size(TabLayoutSpacerSize))
        }
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabLayoutPreview() {
    PinterestCloneTheme {
        TabLayout(modifier = Modifier)
    }
}

