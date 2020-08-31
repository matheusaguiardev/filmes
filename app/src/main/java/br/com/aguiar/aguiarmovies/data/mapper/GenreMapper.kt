package br.com.aguiar.aguiarmovies.data.mapper

import br.com.aguiar.aguiarmovies.data.model.genres.GenreJson
import br.com.aguiar.aguiarmovies.data.model.genres.GenresResponseJson
import br.com.aguiar.aguiarmovies.domain.model.genres.GenreDetail
import br.com.aguiar.aguiarmovies.domain.model.genres.Genres

fun GenresResponseJson.toDomain() = Genres(
    genresList = genresList.map { it.toDomain() }
)

fun GenreJson.toDomain() = GenreDetail(
    id = id,
    name = name
)