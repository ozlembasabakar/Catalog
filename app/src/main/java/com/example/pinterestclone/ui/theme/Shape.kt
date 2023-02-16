package com.example.pinterestclone.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val BottomBarCornerRadius = Shapes.small
val BottomBarHorizontalPadding = PaddingValues(12.dp)
val BottomBarStartPadding = PaddingValues(4.dp)
val BottomBarEndPadding = PaddingValues(16.dp)
val BottomBarPaddingBetweenItems = PaddingValues(8.dp)
val BottomBarItemAlignment = Alignment.Center
val BottomBarItemsAlignment = Alignment.CenterHorizontally
val BottomBarHeight = Modifier.height(80.dp)
val BottomBarWidth = Modifier.fillMaxWidth()