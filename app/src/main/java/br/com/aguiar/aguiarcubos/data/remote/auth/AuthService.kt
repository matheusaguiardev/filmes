package br.com.aguiar.aguiarcubos.data.remote.auth

import br.com.aguiar.aguiarcubos.data.model.auth.AuthResponseJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {

    @GET("authentication/token/new")
    fun loginAuthentication(
        @Query("api_key") apiKey: String
    ): Deferred<Response<AuthResponseJson>>

}