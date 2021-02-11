package com.vladislawfox.scout.presentation.movies

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.vladislawfox.scout.base.extentions.inflateVB
import com.vladislawfox.scout.base.ui.BaseFragment
import com.vladislawfox.scout.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun createBinding(parent: ViewGroup?): FragmentMoviesBinding = inflateVB(parent)
}