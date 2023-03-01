package com.example.pinterestclone.tabs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinterestclone.retrofit.CatCategory
import com.example.pinterestclone.ui.theme.PinterestCloneTheme
import com.example.pinterestclone.ui.theme.TabLayoutPadding
import com.example.pinterestclone.ui.theme.TabLayoutSpacerSize

@Composable
fun Tabs(modifier: Modifier, category: List<CatCategory>) {
    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(TabLayoutPadding)
    ) {
        items(category) { category ->
            TabItem(category = category.name)
            Spacer(modifier = Modifier.size(TabLayoutSpacerSize))
        }
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabsPreview() {
    PinterestCloneTheme {

        val categories = listOf(
            CatCategory(id = 0, name = "Travel"),
            CatCategory(id = 0, name = "Animal"),
            CatCategory(id = 0, name = "Art"),
            CatCategory(id = 0, name = "Food"),
            CatCategory(id = 0, name = "Health"),
            CatCategory(id = 0, name = "Sport"),
            CatCategory(id = 0, name = "Game"),
            CatCategory(id = 0, name = "DIY"),
        )

        Tabs(
            modifier = Modifier,
            category = categories
        )
    }
}

