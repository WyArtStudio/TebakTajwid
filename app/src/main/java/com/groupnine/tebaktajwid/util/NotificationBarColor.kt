package com.groupnine.tebaktajwid.util

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat

class NotificationBarColor {

    fun setColor(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity,color)
        }
    }
}