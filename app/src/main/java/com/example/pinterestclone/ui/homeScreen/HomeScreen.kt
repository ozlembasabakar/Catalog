package com.example.pinterestclone.ui.homeScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinterestclone.R
import com.example.pinterestclone.ui.homeScreen.bottomBar.BottomBar
import com.example.pinterestclone.ui.homeScreen.postCard.PostCard
import com.example.pinterestclone.ui.homeScreen.tabLayout.TabLayout
import com.example.pinterestclone.ui.theme.PinterestCloneTheme

@Composable
fun HomeScreen(modifier: Modifier) {

    val items: List<Int> = listOf(
        R.drawable.images_1,
        R.drawable.images_2,
        R.drawable.images_3,
        R.drawable.images_4,
        R.drawable.images_5,
        R.drawable.images_1,
        R.drawable.images_2,
        R.drawable.images_3,
        R.drawable.images_4,
        R.drawable.images_5,
        R.drawable.images_1,
        R.drawable.images_2,
        R.drawable.images_3,
        R.drawable.images_4,
        R.drawable.images_5,
        R.drawable.images_1,
        R.drawable.images_2,
        R.drawable.images_3,
        R.drawable.images_4,
        R.drawable.images_5,
    )

    Scaffold(
        bottomBar = {
            BottomBar(modifier = modifier)
        },
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column {
            TabLayout(
                modifier = Modifier
            )
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StaggeredVerticalGrid(
                    numColumns = 2,
                    modifier = Modifier,
                ) {
                    items.forEach { icon ->
                        PostCard(
                            modifier = Modifier,
                            image = icon
                        )
                    }
                }
            }
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