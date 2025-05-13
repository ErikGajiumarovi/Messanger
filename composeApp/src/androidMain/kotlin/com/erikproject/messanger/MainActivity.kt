package com.erikproject.messanger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erikproject.messanger.platform_specific.KmpInitializer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KmpInitializer.init(applicationContext, this)
        initKoin()
        setContent {
            App()
        }
    }
}
