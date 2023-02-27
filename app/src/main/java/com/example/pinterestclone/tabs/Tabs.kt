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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinterestclone.R
import com.example.pinterestclone.ui.theme.PinterestCloneTheme
import com.example.pinterestclone.ui.theme.TabLayoutPadding
import com.example.pinterestclone.ui.theme.TabLayoutSpacerSize

@Composable
fun Tabs(modifier: Modifier, category: Array<String>) {
    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(TabLayoutPadding)
    ) {
        items(category) {
            TabItem(category = it)
            Spacer(modifier = Modifier.size(TabLayoutSpacerSize))
        }
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabsPreview() {
    PinterestCloneTheme {
        Tabs(
            modifier = Modifier,
            category = stringArrayResource(id = R.array.category)
        )
    }
}

