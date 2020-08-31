package br.com.aguiar.aguiarmovies.domain.model.movies

data class MovieList(
    val result: List<MovieDetail>,
    val page: Int,
    val totalResults: Int,
    val totalPages: Int
)