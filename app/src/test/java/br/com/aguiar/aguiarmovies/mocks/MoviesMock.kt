package br.com.aguiar.aguiarmovies.mocks

import br.com.aguiar.aguiarmovies.domain.model.movies.MovieDetail
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList

fun getMovieMock() = MovieList(
    listOf(getMoviesDetails()),
    1,
    2,
    3

)

fun getMoviesDetails() = MovieDetail(
    0,
    2,
    1.1,
    "filme teste",
    1.5,
    "titulo original",
    listOf(123, 332, 443),
    "Descrição do filme",
    "01-01-2000",
    "/caminho/do/filme/remoto"
)