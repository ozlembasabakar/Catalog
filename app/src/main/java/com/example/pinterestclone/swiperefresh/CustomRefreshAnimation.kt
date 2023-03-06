package com.example.pinterestclone.swiperefresh

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import com.example.pinterestclone.R
import com.example.pinterestclone.ui.theme.CustomRefreshAnimationPadding
import com.example.pinterestclone.ui.theme.CustomRefreshAnimationSize

@Composable
fun CustomRefreshAnimation(
    modifier: Modifier = Modifier,
    isRefreshing: () -> Boolean,
    willRefresh: () -> Boolean,
    offsetProgress: () -> Float,
) {
    Box(
        modifier = modifier
            .size(CustomRefreshAnimationSize)
            .padding(CustomRefreshAnimationPadding),
        contentAlignment = Alignment.Center
    ) {
        CircleWithRing(
            isRefreshing = isRefreshing(),
            willRefresh = willRefresh(),
            offsetProgress = offsetProgress(),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun CircleWithRing(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    willRefresh: Boolean,
    offsetProgress: Float,
    shape: Shape = CircleShape,
    color: Color,
) {

    val scale by animateFloatAsState(
        targetValue = if (willRefresh) 1f else .8f,
        animationSpec = spring(
            dampingRatio = Spring.StiffnessHigh
        )
    )
    val rotateTransition = rememberInfiniteTransition()

    val rotation by when {
        isRefreshing -> rotateTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Restart,
            )
        )
        else -> remember { mutableStateOf(0f) }
    }

    Box(modifier = modifier
        .scale(scale)
        .clip(shape)
    ) {
        Box(
            modifier = Modifier
                .rotate(rotation)
                .align(Alignment.Center)
                .scale(offsetProgress * 1.5f)
                .fillMaxSize()
                .background(color = color)
        )

        Box(
            modifier = Modifier
                .rotate(rotation)
                .align(Alignment.Center)
                .scale(offsetProgress)
                .clip(shape)
                .background(color = color)
                .fillMaxSize()
        ){
            Icon(
                painter = painterResource(id = R.drawable.arrows),
                contentDescription = null,
                modifier = Modifier
                    .padding(CustomRefreshAnimationPadding)
                    .rotate(rotation)
                    .align(Alignment.Center)
                    .scale(offsetProgress)
                    .clip(shape)
                    .background(color = color),
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}