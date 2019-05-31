package br.com.aguiar.aguiarcubos.domain.repository.genres

import br.com.aguiar.aguiarcubos.domain.model.genres.Genres

interface GenresRepository {
    suspend fun fetchGenres(): Genres
}