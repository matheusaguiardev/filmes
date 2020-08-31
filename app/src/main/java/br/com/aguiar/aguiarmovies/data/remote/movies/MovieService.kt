package br.com.aguiar.aguiarmovies.data.remote.movies

import br.com.aguiar.aguiarmovies.data.model.movies.MovieResponseJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun fetchMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("with_genres") withGenresId: List<Int>,
        @Query("language") language: String = "pt-BR"
    ): Deferred<Response<MovieResponseJson>>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("language") language: String = "pt-BR"
    ): Deferred<Response<MovieResponseJson>>

}