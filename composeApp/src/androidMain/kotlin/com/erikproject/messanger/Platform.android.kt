// androidMain/kotlin/com/erikproject/messanger/WebView.android.kt
package com.erikproject.messanger

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun WebView(
    modifier: Modifier,
    url: String
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
        },
        modifier = modifier,
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}