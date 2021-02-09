package com.vladislawfox.scout.base.extentions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.provider.Settings
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.toast(@StringRes res: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, res, duration).show()
}

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.openPlayStore() {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        )
    )
}

fun Context.openAppSettings() {
    val appSettingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }
    startActivity(appSettingsIntent)
}

fun Context.getAttributeResId(@AttrRes resId: Int): Int? {
    return getAttribute(resId)?.resourceId
}

fun Context.getAttributeData(@AttrRes resId: Int): Int? {
    return getAttribute(resId)?.data
}

fun Context.getAttributeDimensPixel(@AttrRes resId: Int): Float? {
    return getAttribute(resId)?.getDimension(resources.displayMetrics)
}

fun Context.getAttribute(@AttrRes resId: Int): TypedValue? {
    val typedValue = TypedValue()

    return if (theme.resolveAttribute(resId, typedValue, true)) {
        typedValue
    } else {
        null
    }
}

fun Context.getDimensPixel(@DimenRes res: Int): Int {
    return resources.getDimensionPixelSize(res)
}

fun Context.getCompatColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.getScreenWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val size = Point()
    wm.defaultDisplay.getSize(size)
    return size.x
}

fun Context.hideKeyboard(inputView: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(inputView.windowToken, 0)
}

fun Context.showKeyboard(inputView: View) {
    if (inputView.requestFocus()) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(inputView, InputMethodManager.SHOW_IMPLICIT)
    }
}