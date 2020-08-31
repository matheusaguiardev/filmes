package br.com.aguiar.aguiarmovies.data.repository.auth

import br.com.aguiar.aguiarmovies.data.mapper.toDomain
import br.com.aguiar.aguiarmovies.data.model.auth.AuthResponseJson
import br.com.aguiar.aguiarmovies.data.remote.auth.AuthService
import br.com.aguiar.aguiarmovies.data.utils.Constants
import br.com.aguiar.aguiarmovies.domain.model.auth.Auth
import br.com.aguiar.aguiarmovies.domain.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.*

class AuthDataRepository(
    private val service: AuthService
) : AuthRepository {

    override suspend fun login(): Auth {
        val result = withContext(Dispatchers.IO) {
            async { loginApi(Constants.AUTH_KEY) }
        }.await()
        return result.toDomain()
    }

    private suspend fun loginApi(apiKey: String): AuthResponseJson {
        val result = service
            .loginAuthentication(apiKey)
            .await()
        return result.body() ?: AuthResponseJson(false, Date(), "")
    }

}