package com.example.pinterestclone

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.PostViewModel
import com.example.pinterestclone.homeScreen.HomeScreen
import com.example.pinterestclone.tabs.TabsViewModel

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition", "RememberReturnType")
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CatalogNavHost() {

    val navController = rememberNavController()

    val tabsViewModel: TabsViewModel = hiltViewModel()
    val tabsViewState by tabsViewModel.state.collectAsStateWithLifecycle()

    val topics = mutableStateOf(
        tabsViewModel.selectedItem.value
    ).toString()
        .lowercase()
        .replace("mutablestate(value=", "")
        .replace(" & ", "-")
        .replace(" ", "-")
        .replace("interiors", "interior")
        .split(")")[0]
    Log.d("ozlem", topics)

    val postViewModel: PostViewModel = hiltViewModel()
    val postViewState by postViewModel.state.collectAsStateWithLifecycle()

    val isRefreshing by postViewModel.isRefreshing.collectAsStateWithLifecycle()

    LaunchedEffect(topics) {
        when (topics) {
            "wallpapers" -> postViewModel.networkCall("wallpapers")
            "3d-renders" -> postViewModel.networkCall("3d-renders")
            "travel" -> postViewModel.networkCall("travel")
            "nature" -> postViewModel.networkCall("nature")
            "street-photography" -> postViewModel.networkCall("street-photography")
            "experimental" -> postViewModel.networkCall("experimental")
            "animals" -> postViewModel.networkCall("animals")
            "textures-patterns" -> postViewModel.networkCall("textures-patterns")
            "fashion-beauty" -> postViewModel.networkCall("fashion-beauty")
            else -> postViewModel.networkCall("architecture-interior")

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
                post = postViewState.image,
                isRefreshing = isRefreshing,
                onRefresh = {
                    postViewModel.networkCall(topics)
                },
                selectedItem = tabsViewModel.selectedItem
            )
        }
    }
}

sealed class Screen(
    val route: String,
) {
    object HomeScreen : Screen("home_screen")
}
