package com.vladislawfox.scout.domain.mapper

import com.vladislawfox.scout.base.mapper.Mapper
import com.vladislawfox.scout.data.network.model.movies.MovieApiModel
import com.vladislawfox.scout.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieApiModel, Movie> {
    override fun map(from: MovieApiModel): Movie {
        return with(from) {
            Movie(
                posterPath = posterPath,
                adult = adult,
                overview = overview,
                releaseDate = releaseDate,
                genreIds = genreIds,
                id = id,
                originalTitle = originalTitle,
                originalLanguage = originalLanguage,
                title = title,
                backdropPath = backdropPath,
                popularity = popularity,
                voteCount = voteCount,
                video = video,
                voteAverage = voteAverage
            )
        }
    }
}