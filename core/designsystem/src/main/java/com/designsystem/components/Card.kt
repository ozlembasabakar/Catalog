package com.designsystem.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.designsystem.icon.AppIcons
import com.designsystem.theme.CardRowPadding
import com.designsystem.theme.Heart
import com.designsystem.theme.PinterestCloneTheme
import com.designsystem.theme.Shapes
import com.example.designsystem.R

private const val THUMBNAIL_DIMENSION = 50

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Card(
    modifier: Modifier,
    model: String?,
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
            model = model,
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
                .placeholder(AppIcons.PlaceholderIcon)
        }
        if (description != null) {
            Text(text = description, color = MaterialTheme.colorScheme.onSurface)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = CardRowPadding,
                    end = CardRowPadding,
                    top = CardRowPadding
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (likes != 0) {
                Row {
                    Icon(
                        painter = painterResource(id = AppIcons.HeartIcon),
                        contentDescription = stringResource(R.string.likes),
                        tint = Heart,
                        modifier = Modifier
                            .padding(end = CardRowPadding)
                            .testTag("PostCardLikes")
                    )
                    Text(
                        text = "$likes",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = CardRowPadding)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = AppIcons.MoreIcon),
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
        Card(
            modifier = Modifier,
            model = AppIcons.PseudoImageOne.toString(),
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
