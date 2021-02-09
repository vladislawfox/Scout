package com.vladislawfox.scout.base.extentions

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope

val Fragment.viewLifecycleScope: CoroutineScope
    get() = viewLifecycleOwner.lifecycleScope

fun Fragment.toast(@StringRes res: Int, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().toast(res, duration)
}

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    requireActivity().toast(text, duration)
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Fragment.showKeyboard(inputView: View) {
    activity?.showKeyboard(inputView)
}

fun Fragment.getColorCompat(@ColorRes resId: Int): Int {
    return requireContext().getCompatColor(resId)
}

inline fun <reified T : ViewBinding> Fragment.inflateVB(
    parent: ViewGroup?,
    attachToParent: Boolean = false
): T {
    return layoutInflater.inflateVB(parent, attachToParent)
}
