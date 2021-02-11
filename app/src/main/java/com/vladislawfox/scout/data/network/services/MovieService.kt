package com.vladislawfox.scout.data.network.services

import com.vladislawfox.scout.data.network.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("page") page: Int): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("page") page: Int): MoviesResponse
}