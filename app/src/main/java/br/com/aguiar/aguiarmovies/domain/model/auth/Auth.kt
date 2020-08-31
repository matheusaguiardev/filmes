package br.com.aguiar.aguiarmovies.domain.model.auth

import java.util.*

data class Auth(
    val success: Boolean,
    val expiresAt: Date,
    val requestToken: String
)