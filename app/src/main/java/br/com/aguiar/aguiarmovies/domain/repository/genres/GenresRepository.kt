package br.com.aguiar.aguiarmovies.domain.repository.genres

import br.com.aguiar.aguiarmovies.domain.model.genres.Genres

interface GenresRepository {
    suspend fun fetchGenres(): Genres
}