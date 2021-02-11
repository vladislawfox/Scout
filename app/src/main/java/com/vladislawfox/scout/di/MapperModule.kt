package com.vladislawfox.scout.di

import com.vladislawfox.scout.base.mapper.Mapper
import com.vladislawfox.scout.data.network.model.movies.MovieApiModel
import com.vladislawfox.scout.domain.mapper.MovieMapper
import com.vladislawfox.scout.domain.model.Movie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindMovieMapper(mapper: MovieMapper): Mapper<MovieApiModel, Movie>

}