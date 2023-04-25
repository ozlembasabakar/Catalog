package com.example.pinterestclone

import android.annotation.SuppressLint
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
import com.example.PostScreen
import com.example.PostScreenViewModel

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition", "RememberReturnType")
@Composable
fun CatalogNavHost() {

    val navController = rememberNavController()

    val postScreenViewModel: PostScreenViewModel = hiltViewModel()

    val tabsViewState by postScreenViewModel.tabsState.collectAsStateWithLifecycle()
    val postWithTopicsViewState by postScreenViewModel.postState.collectAsStateWithLifecycle()
    val isRefreshing by postScreenViewModel.isRefreshing.collectAsStateWithLifecycle()

    val category = mutableStateOf(
        postScreenViewModel.selectedItem.value
    ).toString()
        .lowercase()
        .replace("mutablestate(value=", "")
        .replace(" & ", "-")
        .replace(" ", "-")
        .replace("interiors", "interior")
        .split(")")[0]

    LaunchedEffect(category) {
        postScreenViewModel.getAllPostsInfoByCategoryFromDatabase(category)
    }

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            PostScreen(
                modifier = Modifier,
                category = tabsViewState.category,
                post = postWithTopicsViewState.info,
                isRefreshing = isRefreshing,
                onRefresh = {
                    postScreenViewModel.insertNewPostsInfoByCategory(category)
                },
                selectedItem = postScreenViewModel.selectedItem,
            )
        }
    }
}

sealed class Screen(
    val route: String,
) {
    object HomeScreen : Screen("home_screen")
}
