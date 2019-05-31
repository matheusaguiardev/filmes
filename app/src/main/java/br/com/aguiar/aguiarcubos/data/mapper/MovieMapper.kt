package br.com.aguiar.aguiarcubos.data.mapper

import br.com.aguiar.aguiarcubos.data.model.movies.MovieDetailJson
import br.com.aguiar.aguiarcubos.data.model.movies.MovieResponseJson
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList


fun MovieResponseJson.toDomain() = MovieList(
    result = result.map { it.toDomain() },
    page = page,
    totalPages = totalPages,
    totalResults = totalResults
)

fun MovieDetailJson.toDomain() = MovieDetail(
    id = id,
    voteCount = voteCount,
    voteAverage = voteAverage,
    title = title,
    popularity = popularity,
    originalTitle = originalTitle,
    genreIds = genreIds,
    overview = overview,
    releaseDate = releaseDate,
    posterPath = posterPath
)