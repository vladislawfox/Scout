package com.vladislawfox.scout.domain.usecases.movies

import com.vladislawfox.scout.base.data.Page
import com.vladislawfox.scout.base.mapper.Mapper
import com.vladislawfox.scout.base.usecase.UseCase
import com.vladislawfox.scout.data.repositories.MoviesRepository
import com.vladislawfox.scout.data.network.model.movies.MovieApiModel
import com.vladislawfox.scout.domain.model.Movie
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val mapper: Mapper<MovieApiModel, Movie>
) : UseCase<Int, Page<Movie>>() {

    override suspend fun execute(params: Int): Page<Movie> {
        val page = moviesRepository.getUpcoming(params)
        return Page(page.page, page.totalPage, mapper.map(page.items))
    }
}