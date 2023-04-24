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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.designsystem.theme.BottomBarItemSize
import com.example.designsystem.theme.PinterestCloneTheme
import com.example.designsystem.theme.Shapes
import com.example.designsystem.R
import com.example.designsystem.icon.AppIcons

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
            contentDescription = stringResource(R.string.bottombaritem),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomBarItemPreview() {
    PinterestCloneTheme {
        BottomBarItem(Modifier, icon = AppIcons.HomeIcon)
    }
}