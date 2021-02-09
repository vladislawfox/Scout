package com.vladislawfox.scout.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.DisplayCutoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.vladislawfox.scout.base.utils.InsetsUtils

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    protected val bindingNullable: VB?
        get() = _binding

    protected abstract fun createBinding(parent: ViewGroup?): VB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InsetsUtils.handleInsets(view) { left, top, right, bottom ->
            handleInsets(left, top, right, bottom)
        }

        InsetsUtils.handleAndroidCutOut(requireActivity()) {
            handleAndroidCutOut(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it, null)
        }
        _binding = null
    }

    protected open fun handleInsets(left: Int, top: Int, right: Int, bottom: Int) {
        view?.updatePadding(left, top, right, bottom)
    }

    protected open fun handleAndroidCutOut(displayCutout: DisplayCutoutCompat) {
    }
}