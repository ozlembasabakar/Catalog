package com.example.pinterestclone.bottombar

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinterestclone.R
import com.example.pinterestclone.ui.theme.BottomBarItemSize
import com.example.ui.theme.PinterestCloneTheme
import com.example.ui.theme.Shapes

@Composable
fun BottomBarItem(
    modifier: Modifier,
    icon: Int,
) {
    Box(
        modifier = modifier
            .size(BottomBarItemSize)
            .fillMaxSize()
            .clip(shape = Shapes.small)
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "BottomBarItem",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomBarItemPreview() {
    PinterestCloneTheme {
        BottomBarItem(Modifier, icon = R.drawable.home_icon)
    }
}