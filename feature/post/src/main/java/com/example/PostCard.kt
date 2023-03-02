package com.example

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.post.R
import com.example.ui.theme.PinterestCloneTheme
import com.example.ui.theme.Shapes
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PostCard(
    modifier: Modifier,
    image: String?,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(Shapes.small)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        GlideImage(
            imageModel = image,
            contentDescription = "",
            previewPlaceholder = R.drawable.images_1,
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.small),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = "More options",
                tint = MaterialTheme.colorScheme.onSurface,
                //modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostCardPreview() {
    PinterestCloneTheme {
        //PostCard(modifier = Modifier, image = R.drawable.images_1)
    }
}
