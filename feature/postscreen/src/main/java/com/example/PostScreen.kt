package com.example

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.components.Card
import com.designsystem.components.PullToRefresh
import com.designsystem.components.Tabs
import com.designsystem.icon.AppIcons
import com.designsystem.theme.*
import com.example.model.Category
import com.example.model.PostInfoWithCategory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun PostScreen(
    modifier: Modifier,
    category: List<Category>,
    post: List<PostInfoWithCategory>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    selectedItem: MutableState<String>,
) {

    Scaffold(
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
                    ),
                category = category,
                selectedItem = selectedItem
            )
            PullToRefresh(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            ) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(HomeScreenStaggeredGridCells),
                    horizontalArrangement = Arrangement.spacedBy(HomeScreenHorizontalPadding),
                    verticalItemSpacing = HomeScreenVerticalPadding,
                    content =
                    {
                        items(post) {
                            Card(
                                modifier = modifier,
                                model = it.url,
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
fun PostScreenPreview() {
    PinterestCloneTheme {

        val selectedItem = remember {
            mutableStateOf("Category1")
        }
        val category = listOf(
            Category(id = "0", title = "Category1", slug = ""),
            Category(id = "0", title = "Category2", slug = ""),
            Category(id = "0", title = "Category3", slug = ""),
            Category(id = "0", title = "Category4", slug = ""),
            Category(id = "0", title = "Category5", slug = ""),
            Category(id = "0", title = "Category6", slug = ""),
            Category(id = "0", title = "Category7", slug = ""),
            Category(id = "0", title = "Category8", slug = ""),
            Category(id = "0", title = "Category9", slug = ""),
            Category(id = "0", title = "Category10", slug = ""),
        )
        val post = listOf(
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageOne.toString(),
                likes = 1,
                category = "Category1",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageTwo.toString(),
                likes = 1,
                category = "Category2",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageThree.toString(),
                likes = 1,
                category = "Category3",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageFour.toString(),
                likes = 1,
                category = "Category4",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageFive.toString(),
                likes = 1,
                category = "Category5",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageOne.toString(),
                likes = 1,
                category = "Category6",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageTwo.toString(),
                likes = 1,
                category = "Category7",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageThree.toString(),
                likes = 1,
                category = "Category8",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageFour.toString(),
                likes = 1,
                category = "Category9",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageFive.toString(),
                likes = 1,
                category = "Category10",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageOne.toString(),
                likes = 1,
                category = "Category11",
                description = ""
            ),
            PostInfoWithCategory(
                id = "1",
                url = AppIcons.PseudoImageTwo.toString(),
                likes = 1,
                category = "Category12",
                description = ""
            ),
        )

        PostScreen(
            Modifier,
            category = category,
            post = post,
            onRefresh = {},
            isRefreshing = false,
            selectedItem = selectedItem
        )
    }
}