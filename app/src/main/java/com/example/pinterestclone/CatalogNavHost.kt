package com.example.pinterestclone

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.PostViewModel
import com.example.pinterestclone.homeScreen.HomeScreen
import com.example.pinterestclone.tabs.TabsViewModel

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition", "RememberReturnType")
@Composable
fun CatalogNavHost() {

    val navController = rememberNavController()

    val tabsViewModel: TabsViewModel = hiltViewModel()
    val tabsViewState by tabsViewModel.state.collectAsStateWithLifecycle()

    val postViewModel: PostViewModel = hiltViewModel()
    val postWithTopicsViewState by postViewModel.state.collectAsStateWithLifecycle()
    val isRefreshing by postViewModel.isRefreshing.collectAsStateWithLifecycle()

    val category = mutableStateOf(
        tabsViewModel.selectedItem.value
    ).toString()
        .lowercase()
        .replace("mutablestate(value=", "")
        .replace(" & ", "-")
        .replace(" ", "-")
        .replace("interiors", "interior")
        .split(")")[0]

    Log.d("ozlem", "category: $category")

    LaunchedEffect(category) {
        tabsViewModel.getAllCategoriesFromDatabase()
        postViewModel.getAllPostsInfoByCategoryFromDatabase(category)
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
                    Log.d(
                        "ozlem",
                        "postWithTopicsViewState in onRefresh: ${postWithTopicsViewState.info}"
                    )
                    postViewModel.insertNewPostsInfoByCategory(category)
                },
                selectedItem = tabsViewModel.selectedItem,
            )
            Log.d(
                "ozlem",
                "postWithTopicsViewState in LaunchedEffect: ${postWithTopicsViewState.info}"
            )
        }
    }
}

sealed class Screen(
    val route: String,
) {
    object HomeScreen : Screen("home_screen")
}
