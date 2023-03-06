package com.example.pinterestclone.tabs

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.Category
import com.example.pinterestclone.ui.theme.PinterestCloneTheme
import com.example.pinterestclone.ui.theme.TabsHorizontalPadding
import com.example.pinterestclone.ui.theme.TabsSpacerSize
import com.example.pinterestclone.ui.theme.TabsVerticalPadding

@Composable
fun Tabs(modifier: Modifier, category: List<Category>) {
    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                vertical = TabsVerticalPadding,
                horizontal = TabsHorizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(TabsHorizontalPadding
        ),
    ) {
        items(category) { category ->
            TabItem(
                category = category.name
            )
            Spacer(modifier = Modifier.size(TabsSpacerSize))
        }
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabsPreview() {
    PinterestCloneTheme {

        val categories = listOf(
            Category(id = 0, name = "Travel"),
            Category(id = 0, name = "Animal"),
            Category(id = 0, name = "Art"),
            Category(id = 0, name = "Food"),
            Category(id = 0, name = "Health"),
            Category(id = 0, name = "Sport"),
            Category(id = 0, name = "Game"),
            Category(id = 0, name = "DIY"),
        )

        Tabs(
            modifier = Modifier,
            category = categories
        )
    }
}

