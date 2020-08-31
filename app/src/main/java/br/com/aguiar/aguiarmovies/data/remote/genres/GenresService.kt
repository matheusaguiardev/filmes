package br.com.aguiar.aguiarmovies.data.remote.genres

import br.com.aguiar.aguiarmovies.data.model.genres.GenresResponseJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresService {

    @GET("genre/movie/list")
    fun fetchGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): Deferred<Response<GenresResponseJson>>

}