package com.vladislawfox.scout.presentation.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.vladislawfox.scout.base.ui.BaseViewModel
import com.vladislawfox.scout.domain.sources.NowPlayingMoviesSource
import com.vladislawfox.scout.domain.sources.PopularMoviesSource
import com.vladislawfox.scout.domain.sources.TopRatedMoviesSource
import com.vladislawfox.scout.domain.sources.UpcomingMoviesSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val nowPlayingMoviesSource: NowPlayingMoviesSource,
    private val popularMoviesSource: PopularMoviesSource,
    private val topRatedMoviesSource: TopRatedMoviesSource,
    private val upcomingMoviesSource: UpcomingMoviesSource
) : BaseViewModel() {

    val nowPlayingMovies = Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
        nowPlayingMoviesSource
    }.flow.cachedIn(scope)

    val popularMovies = Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
        popularMoviesSource
    }.flow.cachedIn(scope)

    val topRatedMovies = Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
        topRatedMoviesSource
    }.flow.cachedIn(scope)

    val upcomingMovies = Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
        upcomingMoviesSource
    }.flow.cachedIn(scope)

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
}