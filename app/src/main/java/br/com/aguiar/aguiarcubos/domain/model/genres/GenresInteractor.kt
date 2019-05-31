package br.com.aguiar.aguiarcubos.domain.model.genres

import br.com.aguiar.aguiarcubos.domain.repository.genres.GenresRepository

class GenresInteractor(
    private val repository: GenresRepository
) {

    suspend operator fun invoke(): Genres {
        return repository.fetchGenres()
    }

}