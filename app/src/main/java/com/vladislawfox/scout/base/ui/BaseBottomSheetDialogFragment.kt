package com.vladislawfox.scout.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    protected abstract fun createBinding(parent: ViewGroup?): VB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}