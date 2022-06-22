package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetest.pathway.CustomTheme
import com.example.composetest.pathway.Home
import com.example.composetest.pathway.MainStateCodeLabScreenInstance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme(dark = false) {
                Home()
            }
        }
    }
}