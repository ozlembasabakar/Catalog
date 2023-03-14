package com.example.pinterestclone.homeScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.PostCard
import com.example.model.Category
import com.example.model.PostInfo
import com.example.model.Urls
import com.example.pinterestclone.R
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
    post: List<PostInfo>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
) {

    val context = LocalContext.current

    if (!checkForInternet(context)) {
        Toast
            .makeText(context, stringResource(R.string.toastMessage), Toast.LENGTH_LONG)
            .show()
    }

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
                    ),
                category = category
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
                        items(post.size) {
                            PostCard(modifier = modifier, image = post[it].urls.regular)
                        }
                    }
                )
            }
        }
    }
}

private fun checkForInternet(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }
}

@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    PinterestCloneTheme {

        val category = listOf(
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
        )
    }
}