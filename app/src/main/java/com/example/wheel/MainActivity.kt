package com.example.wheel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.wheel.screen.WheelScreen
import com.example.wheel.ui.theme.WheelTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WheelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    it
                    WheelScreen()
                }
            }
        }
    }
}









