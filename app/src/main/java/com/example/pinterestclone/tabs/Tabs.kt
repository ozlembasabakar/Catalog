package com.example.pinterestclone.tabs

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.theme.PinterestCloneTheme
import com.example.designsystem.theme.TabsHorizontalPadding
import com.example.designsystem.theme.TabsVerticalPadding
import com.example.model.Category

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Tabs(
    modifier: Modifier,
    category: List<Category>,
    selectedItem: MutableState<String>,
) {

    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                vertical = TabsVerticalPadding,
                horizontal = TabsHorizontalPadding
            ),
        horizontalArrangement = Arrangement.spacedBy(
            TabsHorizontalPadding
        ),
    ) {
        items(category) { category ->
            TabItem(
                modifier = Modifier,
                category = category.title,
                isSelected = selectedItem.value == category.title,
                onClick = {
                    selectedItem.value = it
                }
            )
        }
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabsPreview() {
    PinterestCloneTheme {

        val selectedItem = remember {
            mutableStateOf("Animal")
        }
        val categories = listOf(
            Category(id = "0", slug = "Travel", title = "Travel"),
            Category(id = "0", slug = "Travel", title = "Animal"),
            Category(id = "0", slug = "Travel", title = "Art"),
            Category(id = "0", slug = "Travel", title = "Food"),
            Category(id = "0", slug = "Travel", title = "Health"),
            Category(id = "0", slug = "Travel", title = "Sport"),
            Category(id = "0", slug = "Travel", title = "Game"),
            Category(id = "0", slug = "Travel", title = "DIY"),
        )

        Tabs(
            modifier = Modifier,
            category = categories,
            selectedItem = selectedItem
        )
    }
}

