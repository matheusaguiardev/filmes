package br.com.aguiar.aguiarmovies.data.model.genres

import com.google.gson.annotations.SerializedName

data class GenreJson(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "name") val name: String
)