package br.com.aguiar.aguiarcubos.data.model.movies

import com.google.gson.annotations.SerializedName

data class MovieDetailJson(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "vote_count") val voteCount: Int,
    @SerializedName(value = "vote_average") val voteAverage: Double,
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "popularity") val popularity: Double,
    @SerializedName(value = "original_title") val originalTitle: String,
    @SerializedName(value = "genre_ids") val genreIds: List<Int>,
    @SerializedName(value = "overview") val overview: String,
    @SerializedName(value = "release_date") val releaseDate: String,
    @SerializedName(value = "poster_path") val posterPath: String?

)