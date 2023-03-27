package com.example.pinterestclone

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.PostViewModel
import com.example.pinterestclone.common.checkForInternet
import com.example.pinterestclone.homeScreen.HomeScreen
import com.example.pinterestclone.tabs.TabsViewModel

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition", "RememberReturnType")
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CatalogNavHost() {

    val context = LocalContext.current

    val navController = rememberNavController()

    val tabsViewModel: TabsViewModel = hiltViewModel()
    val tabsViewState by tabsViewModel.state.collectAsStateWithLifecycle()

    val postViewModel: PostViewModel = hiltViewModel()
    val postViewState by postViewModel.state.collectAsStateWithLifecycle()
    val postWithTopicsViewState by postViewModel.stateWithTopics.collectAsStateWithLifecycle()

    val isRefreshing by postViewModel.isRefreshing.collectAsStateWithLifecycle()

    val topics = mutableStateOf(
        tabsViewModel.selectedItem.value
    ).toString()
        .lowercase()
        .replace("mutablestate(value=", "")
        .replace(" & ", "-")
        .replace(" ", "-")
        .replace("interiors", "interior")
        .split(")")[0]

    Log.d("ozlem", "topic: $topics")


    LaunchedEffect(topics) {
        tabsViewModel.fetchCategoryDataFromRepository()
        postViewModel.getPostWithTopics(topics)

        Log.d("ozlem", "postWithTopicsViewState: ${postWithTopicsViewState.info}")

        if (!checkForInternet(context)) {
            Toast
                .makeText(
                    context,
                    "Please check your network connection!",
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                modifier = Modifier,
                category = tabsViewState.category,
                post = postWithTopicsViewState.info,
                isRefreshing = isRefreshing,
                onRefresh = {
                    Log.d("ozlem", "topic in onRefresh: $topics")
                    postViewModel.getNewPostWithTopics(topics)
                },
                selectedItem = tabsViewModel.selectedItem,
            )
        }
    }
}

sealed class Screen(
    val route: String,
) {
    object HomeScreen : Screen("home_screen")
}
