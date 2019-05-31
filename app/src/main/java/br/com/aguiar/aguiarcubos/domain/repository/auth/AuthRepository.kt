package br.com.aguiar.aguiarcubos.domain.repository.auth

import br.com.aguiar.aguiarcubos.domain.model.auth.Auth

interface AuthRepository {
    suspend fun login(): Auth
}