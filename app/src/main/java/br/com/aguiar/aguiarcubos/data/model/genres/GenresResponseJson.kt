package br.com.aguiar.aguiarcubos.data.model.genres

import com.google.gson.annotations.SerializedName

data class GenresResponseJson(
    @SerializedName(value = "genres") val genresList: List<GenreJson>
)