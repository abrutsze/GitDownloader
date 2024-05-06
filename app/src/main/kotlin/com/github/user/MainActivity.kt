package com.github.user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.github.designsystem.theme.MyMaterialTheme
import com.github.navigation.RootNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMaterialTheme {
                RootNavHost()
            }
        }
    }
}