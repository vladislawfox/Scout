package com.vladislawfox.scout.base.utils

import android.app.Activity
import android.view.View
import androidx.core.view.DisplayCutoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

object InsetsUtils {
    fun handleInsets(view: View, callback: (left: Int, top: Int, right: Int, bottom: Int) -> Unit) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            callback.invoke(
                insets.systemWindowInsetLeft,
                insets.systemWindowInsetTop,
                insets.systemWindowInsetRight,
                insets.systemWindowInsetBottom
            )
            insets
        }
        view.requestApplyInsets()
    }

    fun handleAndroidCutOut(
        activity: Activity,
        callback: (displayCutout: DisplayCutoutCompat) -> Unit
    ) {
        val windowInsets = activity.window.decorView.rootWindowInsets?.let { WindowInsetsCompat.toWindowInsetsCompat(it) }
        val displayCutout = windowInsets?.displayCutout ?: return
        callback.invoke(displayCutout)
    }
}