package br.com.aguiar.aguiarcubos.data.model.movies

import com.google.gson.annotations.SerializedName

data class MovieResponseJson(
    @SerializedName(value = "results") val result: List<MovieDetailJson>,
    @SerializedName(value = "page") val page: Int,
    @SerializedName(value = "total_results") val totalResults: Int,
    @SerializedName(value = "total_pages") val totalPages: Int
)