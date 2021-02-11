package com.vladislawfox.scout.data.network.response

import com.google.gson.annotations.SerializedName
import com.vladislawfox.scout.data.network.model.movies.MovieApiModel

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("results")
    val results: List<MovieApiModel>
)
