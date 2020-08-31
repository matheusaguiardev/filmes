package br.com.aguiar.aguiarmovies.data.repository.movies

import br.com.aguiar.aguiarmovies.data.mapper.toDomain
import br.com.aguiar.aguiarmovies.data.model.movies.MovieResponseJson
import br.com.aguiar.aguiarmovies.data.remote.movies.MovieService
import br.com.aguiar.aguiarmovies.data.utils.Constants.AUTH_KEY
import br.com.aguiar.aguiarmovies.domain.model.movies.MovieList
import br.com.aguiar.aguiarmovies.domain.repository.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MoviesDataRepository(
    private val service: MovieService
) : MoviesRepository {

    override suspend fun searchMovie(query: String): MovieList {
        return try {
            val result = withContext(Dispatchers.IO) {
                async { searchMovieApi(AUTH_KEY, query) }
            }.await()
            return result.toDomain()
        } catch (e: Throwable) {
            MovieList(emptyList(), 0, 0, 0)
        }
    }

    override suspend fun fetchMovies(genreId: List<Int>): MovieList {
        return try {
            val result = withContext(Dispatchers.IO) {
                async { movieApi(AUTH_KEY, genreId) }
            }.await()

            result.toDomain()
        } catch (e: Throwable) {
            MovieList(emptyList(), 0, 0, 0)
        }
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