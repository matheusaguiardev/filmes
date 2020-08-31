package br.com.aguiar.aguiarmovies.domain.model.genres

import br.com.aguiar.aguiarmovies.domain.repository.genres.GenresRepository

class GenresInteractor(
    private val repository: GenresRepository
) {

    suspend operator fun invoke(): Genres {
        return repository.fetchGenres()
    }

}