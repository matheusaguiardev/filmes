package br.com.aguiar.aguiarcubos.domain.model.auth

import br.com.aguiar.aguiarcubos.domain.repository.auth.AuthRepository

class AuthInteractor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Auth {
        return repository.login()
    }

}