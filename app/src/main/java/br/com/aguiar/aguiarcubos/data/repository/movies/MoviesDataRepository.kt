package br.com.aguiar.aguiarcubos.data.repository.movies

import br.com.aguiar.aguiarcubos.data.mapper.toDomain
import br.com.aguiar.aguiarcubos.data.model.movies.MovieResponseJson
import br.com.aguiar.aguiarcubos.data.remote.movies.MovieService
import br.com.aguiar.aguiarcubos.data.utils.Constants.AUTH_KEY
import br.com.aguiar.aguiarcubos.domain.model.movies.MovieList
import br.com.aguiar.aguiarcubos.domain.repository.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MoviesDataRepository(
    private val service: MovieService
) : MoviesRepository {

    override suspend fun searchMovie(query: String): MovieList {
        val result = withContext(Dispatchers.IO) {
            async { searchMovieApi(AUTH_KEY, query) }
        }.await()
        return result.toDomain()
    }

    override suspend fun fetchMovies(genreId: List<Int>): MovieList {

        val result = withContext(Dispatchers.IO) {
            async { movieApi(AUTH_KEY, genreId) }
        }.await()
        return result.toDomain()
    }

    private suspend fun searchMovieApi(
        apiKey: String,
        query: String,
        page: Int = 1,
        language: String = "pt-BR"
    ): MovieResponseJson {
        val result = service
            .searchMovie(
                apiKey = apiKey,
                page = page,
                query = query,
                language = language
            ).await()
        return result.body() ?: MovieResponseJson(emptyList(), 0, 0, 0)
    }

    private suspend fun movieApi(
        apiKey: String,
        withGenresId: List<Int>,
        page: Int = 1,
        language: String = "pt-BR"
    ): MovieResponseJson {
        val result = service
            .fetchMovies(
                apiKey,
                page,
                withGenresId,
                language
            ).await()

        return result.body() ?: MovieResponseJson(emptyList(), 0, 0, 0)
    }
}