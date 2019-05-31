package br.com.aguiar.aguiarcubos.domain.model.movies

import br.com.aguiar.aguiarcubos.domain.repository.movies.MoviesRepository

class MoviesInteractor(
    private val repository: MoviesRepository
) {

    suspend operator fun invoke(id: List<Int>): MovieList {
        return repository.fetchMovies(id)
    }

    suspend fun searchMovieByName(name: String): MovieList {
        return repository.searchMovie(name)
    }

}