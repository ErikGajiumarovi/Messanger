package com.erikproject.messanger.platform_specific

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context

// androidMain
@SuppressLint("StaticFieldLeak")
object KmpInitializer {
    lateinit var applicationContext: Context
    lateinit var currentActivity: Activity

    @SuppressLint("StaticFieldLeak")
    fun init(context: Context, activity: Activity) {
        applicationContext = context.applicationContext
        currentActivity = activity
    }
}