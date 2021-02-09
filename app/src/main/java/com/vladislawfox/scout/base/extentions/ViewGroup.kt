package com.vladislawfox.scout.base.extentions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import androidx.viewbinding.ViewBinding

/**
 * Inflate ViewBinding object from the xml layout
 * @param attachToParent - attach create view to parent view group.
 * Use null if layout declared with using <merge></merge> tag
 */
inline fun <reified T : ViewBinding> ViewGroup.inflateVB(
    attachToParent: Boolean? = null
): T {
    return LayoutInflater.from(context).inflateVB(this, attachToParent)
}

fun ViewGroup.runTransition(
    duration: Long = 300L,
    transition: androidx.transition.Transition = AutoTogetherTransition()
) {
    transition.duration = duration
    TransitionManager.beginDelayedTransition(this, transition)
}

class AutoTogetherTransition : TransitionSet() {

    init {
        ordering = ORDERING_TOGETHER
        addTransition(Fade(Fade.OUT))
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.IN))
    }
}