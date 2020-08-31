package br.com.aguiar.aguiarmovies.data.repository.genres

import br.com.aguiar.aguiarmovies.data.mapper.toDomain
import br.com.aguiar.aguiarmovies.data.model.genres.GenresResponseJson
import br.com.aguiar.aguiarmovies.data.remote.genres.GenresService
import br.com.aguiar.aguiarmovies.data.utils.Constants.AUTH_KEY
import br.com.aguiar.aguiarmovies.domain.model.genres.Genres
import br.com.aguiar.aguiarmovies.domain.repository.genres.GenresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GenresDataRepository(
    private val service: GenresService
) : GenresRepository {
    override suspend fun fetchGenres(): Genres {

        val result = withContext(Dispatchers.IO) {
            async { genresApi(AUTH_KEY) }
        }.await()
        return result.toDomain()
    }

    private suspend fun genresApi(apiKey: String): GenresResponseJson {
        val result = service
            .fetchGenres(apiKey).await()
        return result.body() ?: GenresResponseJson(emptyList())
    }

}