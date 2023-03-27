package com.example.pinterestclone.homeScreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.PostCard
import com.example.model.Category
import com.example.model.PostInfoWithCategory
import com.example.pinterestclone.swiperefresh.CustomPullToRefresh
import com.example.pinterestclone.tabs.Tabs
import com.example.pinterestclone.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun HomeScreen(
    modifier: Modifier,
    category: List<Category>,
    post: List<PostInfoWithCategory>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    selectedItem: MutableState<String>,
) {

    Log.d("ozlem", "HomeScreen: ${post.size}")

    Scaffold(
        //bottomBar = {
        //BottomBar(modifier = modifier)
        //},
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = HomeScreenHorizontalPadding
                ),
        ) {
            Tabs(
                modifier = modifier
                    .padding(
                        top = TabsVerticalPadding
                    )
                    .testTag("tabs"),
                category = category,
                selectedItem = selectedItem
            )
            CustomPullToRefresh(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            ) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(HomeScreenStaggeredGridCells),
                    horizontalArrangement = Arrangement.spacedBy(HomeScreenHorizontalPadding),
                    verticalArrangement = Arrangement.spacedBy(HomeScreenVerticalPadding),
                    content =
                    {
                        items(post) {
                            PostCard(
                                modifier = modifier,
                                image = it.url,
                                likes = it.likes,
                                description = it.description
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    PinterestCloneTheme {

/*        val category = listOf(
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
            Category(id = "0", title = "Cat1", slug = ""),
        )
        val post = listOf(
            PostInfo(id = "1", urls = Urls(R.drawable.images_1.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_2.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_3.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_4.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_5.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_1.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_2.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_3.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_4.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_5.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_1.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_2.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_3.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_4.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_5.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_1.toString()), likes = 1),
            PostInfo(id = "1", urls = Urls(R.drawable.images_2.toString()), likes = 1),
        )

        HomeScreen(
            Modifier,
            category = category,
            post = post,
            onRefresh = {},
            isRefreshing = true
        )*/
    }
}