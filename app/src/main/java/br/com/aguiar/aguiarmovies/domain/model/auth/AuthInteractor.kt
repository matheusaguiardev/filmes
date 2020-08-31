package br.com.aguiar.aguiarmovies.domain.model.auth

import br.com.aguiar.aguiarmovies.domain.repository.auth.AuthRepository

class AuthInteractor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Auth {
        return repository.login()
    }

}