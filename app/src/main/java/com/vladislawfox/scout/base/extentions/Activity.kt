package com.vladislawfox.scout.base.extentions

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

// TODO: use WindowInsetsController instead of flags for system and navigation bars for android R and above
// https://developer.android.com/reference/android/view/WindowInsetsController
fun AppCompatActivity.setUpEdgeToEdgeMode() {
    window.decorView.systemUiVisibility +=
            // Tells the system that the window wishes the content to
            // be laid out at the most extreme scenario. See the docs for
            // more  information on the specifics
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                // Tells the system that the window wishes the content to
                // be laid out as if the navigation bar was hidden
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
}