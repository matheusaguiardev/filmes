package br.com.aguiar.aguiarmovies.domain.repository.auth

import br.com.aguiar.aguiarmovies.domain.model.auth.Auth

interface AuthRepository {
    suspend fun login(): Auth
}