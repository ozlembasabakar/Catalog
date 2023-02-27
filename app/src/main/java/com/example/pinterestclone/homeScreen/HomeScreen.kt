package com.example.pinterestclone.homeScreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.PostCard
import com.example.StaggeredVerticalGrid
import com.example.pinterestclone.R
import com.example.pinterestclone.bottombar.BottomBar
import com.example.pinterestclone.tabs.Tabs
import com.example.pinterestclone.ui.theme.PinterestCloneTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
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
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column {
            Tabs(
                modifier = Modifier,
                category = stringArrayResource(R.array.category)
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