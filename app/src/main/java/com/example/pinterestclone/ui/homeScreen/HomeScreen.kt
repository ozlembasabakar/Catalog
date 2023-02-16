package com.example.pinterestclone.ui.homeScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinterestclone.ui.homeScreen.bottomBar.BottomBar
import com.example.pinterestclone.ui.homeScreen.tabLayout.TabLayout
import com.example.pinterestclone.ui.theme.PinterestCloneTheme

@Composable
fun HomeScreen(modifier: Modifier) {
    Scaffold(
        bottomBar = {
            BottomBar(modifier = modifier)
        },
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column {
            TabLayout(modifier = Modifier)
        }
    }
}

@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    PinterestCloneTheme {
        HomeScreen(Modifier)
    }
}