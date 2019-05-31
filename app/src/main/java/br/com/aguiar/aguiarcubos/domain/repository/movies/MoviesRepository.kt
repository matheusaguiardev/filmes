package br.com.aguiar.aguiarcubos.domain.repository.movies

import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList

interface MoviesRepository {
    suspend fun fetchMovies(genreId: List<Int>): MovieList

    suspend fun searchMovie(query: String): MovieList
}