package br.com.aguiar.aguiarcubos.data.mapper

import br.com.aguiar.aguiarcubos.data.model.auth.AuthResponseJson
import br.com.aguiar.aguiarcubos.domain.model.auth.Auth

fun AuthResponseJson.toDomain() = Auth(
    success = success,
    expiresAt = expiresAt,
    requestToken = requestToken
)