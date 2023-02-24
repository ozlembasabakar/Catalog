package com.example.pinterestclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.example.pinterestclone.homeScreen.HomeScreen
import com.example.pinterestclone.ui.theme.PinterestCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PinterestCloneTheme {
                HomeScreen(modifier = Modifier)
            }
        }
    }
}