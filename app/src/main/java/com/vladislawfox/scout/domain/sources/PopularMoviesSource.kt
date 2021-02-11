package com.vladislawfox.scout.domain.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vladislawfox.scout.domain.model.Movie
import com.vladislawfox.scout.domain.usecases.movies.GetPopularMoviesUseCase
import javax.inject.Inject

class PopularMoviesSource @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPage = params.key ?: 1
        val page = getPopularMoviesUseCase.invoke(nextPage)

        return LoadResult.Page(
            data = page.items,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = nextPage + 1
        )
    }
}