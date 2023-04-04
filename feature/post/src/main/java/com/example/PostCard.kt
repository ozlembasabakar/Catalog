package com.example

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.feature.post.R
import com.example.ui.theme.PinterestCloneTheme
import com.example.ui.theme.PostCardRowPadding
import com.example.ui.theme.Shapes

private const val THUMBNAIL_DIMENSION = 50

@SuppressLint("CheckResult")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PostCard(
    modifier: Modifier,
    image: String?,
    likes: Int,
    description: String?,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shapes.small)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        GlideImage(
            model = image,
            contentDescription = stringResource(R.string.image_description),
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.small)
                .testTag("GlideImage"),
            contentScale = ContentScale.Crop,
        ) {
            it.thumbnail(
                it.clone().override(THUMBNAIL_DIMENSION)
            ).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
        }
        if (description != null) {
            Text(text = description, color = MaterialTheme.colorScheme.onSurface)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = PostCardRowPadding,
                    end = PostCardRowPadding,
                    top = PostCardRowPadding
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = stringResource(R.string.likes),
                    tint = if (likes == 0) MaterialTheme.colorScheme.surface else Color.Red,
                    modifier = Modifier.padding(end = PostCardRowPadding).testTag("PostCardLikes")
                )
                Text(
                    text = "$likes",
                    color = if (likes == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = PostCardRowPadding)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = stringResource(R.string.more_options),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}


@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostCardPreview_WithDescriptionAndLikes() {
    PinterestCloneTheme {
        PostCard(
            modifier = Modifier,
            image = R.drawable.images_1.toString(),
            likes = 1,
            description = "Description"
        )
    }
}

/*

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostCardPreview_WithDescription() {
    PinterestCloneTheme {
        PostCard(
            modifier = Modifier,
            image = R.drawable.images_1.toString(),
            likes = 0,
            description = "Description"
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostCardPreview_WithLikes() {
    PinterestCloneTheme {
        PostCard(
            modifier = Modifier,
            image = R.drawable.images_1.toString(),
            likes = 27,
            description = ""
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostCardPreview() {
    PinterestCloneTheme {
        PostCard(
            modifier = Modifier,
            image = R.drawable.images_1.toString(),
            likes = 0,
            description = ""
        )
    }
}
*/
