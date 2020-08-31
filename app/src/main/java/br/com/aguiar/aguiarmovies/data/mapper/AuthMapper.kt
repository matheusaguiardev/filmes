package br.com.aguiar.aguiarmovies.data.mapper

import br.com.aguiar.aguiarmovies.data.model.auth.AuthResponseJson
import br.com.aguiar.aguiarmovies.domain.model.auth.Auth

fun AuthResponseJson.toDomain() = Auth(
    success = success,
    expiresAt = expiresAt,
    requestToken = requestToken
)