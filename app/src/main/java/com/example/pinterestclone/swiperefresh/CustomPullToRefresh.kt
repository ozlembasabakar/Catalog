package com.example.pinterestclone.swiperefresh

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.lang.Float.min
import kotlin.math.roundToInt

@ExperimentalFoundationApi
@Composable
fun CustomPullToRefresh(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {

    val pullState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    var offset by remember { mutableStateOf(0) }
    val animatedOffset by animateIntAsState(
        targetValue = offset,
        animationSpec = spring(stiffness = Spring.StiffnessHigh)
    )

    val density = LocalDensity.current
    val trigger = remember { 120.dp }
    val triggerPx = remember { with(density) { trigger.toPx() } }

    SwipeRefresh(
        modifier = modifier,
        state = pullState,
        onRefresh = onRefresh,
        refreshTriggerDistance = trigger,
        indicator = { state, _ ->

            val willRefresh = state.indicatorOffset.roundToInt() > triggerPx
            offset = state.indicatorOffset.roundToInt() + if (willRefresh) 100 else 0

            offset = when {
                willRefresh -> triggerPx.roundToInt() + (state.indicatorOffset.roundToInt() * .1f).roundToInt()
                state.isRefreshing -> triggerPx.roundToInt()
                else -> state.indicatorOffset.roundToInt()
            }
        }
    ) {
        Box(
            Modifier
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            CustomRefreshAnimation(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .wrapContentWidth(),
                isRefreshing = { pullState.isRefreshing },
                willRefresh = { offset > triggerPx },
                offsetProgress = { min(animatedOffset / triggerPx, 1f) }
            )

            val scale by animateFloatAsState(
                targetValue = if (offset > triggerPx) .95f else 1f,
                animationSpec = spring(
                    dampingRatio = Spring.StiffnessHigh,
                )
            )

            Box(modifier = Modifier
                .scale(scale)
                .offset { IntOffset(x = 0, y = animatedOffset) }
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
            ) {
                content()
            }
        }
    }
}
