package com.vladislawfox.scout.data.repositories

import com.vladislawfox.scout.base.data.Page
import com.vladislawfox.scout.data.network.model.movies.MovieApiModel
import com.vladislawfox.scout.data.network.services.MovieService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getNowPlaying(page: Int): Page<MovieApiModel> {
        val response = movieService.getNowPlaying(page)
        return Page(response.page, response.totalPages, response.results)
    }

    suspend fun getPopular(page: Int): Page<MovieApiModel> {
        val response = movieService.getPopular(page)
        return Page(response.page, response.totalPages, response.results)
    }

    suspend fun getTopRated(page: Int): Page<MovieApiModel> {
        val response = movieService.getTopRated(page)
        return Page(response.page, response.totalPages, response.results)
    }

    suspend fun getUpcoming(page: Int): Page<MovieApiModel> {
        val response = movieService.getUpcoming(page)
        return Page(response.page, response.totalPages, response.results)
    }
}