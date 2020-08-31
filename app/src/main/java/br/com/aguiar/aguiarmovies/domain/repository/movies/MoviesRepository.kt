package br.com.aguiar.aguiarmovies.domain.repository.movies

import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList

interface MoviesRepository {
    suspend fun fetchMovies(genreId: List<Int>): MovieList

    suspend fun searchMovie(query: String): MovieList
}