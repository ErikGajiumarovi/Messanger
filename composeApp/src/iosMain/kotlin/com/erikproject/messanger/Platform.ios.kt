// iosMain/kotlin/com/erikproject/messanger/WebView.ios.kt
package com.erikproject.messanger

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebView(
    modifier: Modifier,
    url: String
) {
    val webView = remember {
        WKWebView(
            frame = CGRectMake(0.0, 0.0, 0.0, 0.0),
            configuration = WKWebViewConfiguration().apply {
            }
        )
    }

    val request = remember(url) {
        NSURL.URLWithString(url)?.let { NSURLRequest.requestWithURL(it) }
    }

    UIKitView(
        factory = { webView },
        modifier = modifier,
        update = { view ->
            request?.let {
                if (view.URL?.absoluteString != url) {
                    view.loadRequest(it)
                }
            }
        }
    )
}