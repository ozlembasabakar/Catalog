package com.example.pinterestclone.homeScreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.StaggeredVerticalGrid
import com.example.pinterestclone.bottombar.BottomBar
import com.example.pinterestclone.tabs.Tabs
import com.example.pinterestclone.ui.theme.PinterestCloneTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.PostCard
import com.example.PostViewModel
import com.example.pinterestclone.tabs.TabsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(modifier: Modifier) {

    val tabsViewModel: TabsViewModel = hiltViewModel()
    val tabsViewState by tabsViewModel.state.collectAsStateWithLifecycle()

    val postViewModel: PostViewModel = hiltViewModel()
    val postViewState by postViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomBar(modifier = modifier)
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column {
            Tabs(
                modifier = Modifier,
                category = tabsViewState.category
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
                    postViewState.image.forEach { icon ->
                        PostCard(
                            modifier = Modifier,
                            image = icon.url
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