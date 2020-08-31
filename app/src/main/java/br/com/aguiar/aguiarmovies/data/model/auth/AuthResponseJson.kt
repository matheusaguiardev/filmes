package br.com.aguiar.aguiarmovies.data.model.auth

import com.google.gson.annotations.SerializedName
import java.util.*

data class AuthResponseJson(
    @SerializedName(value = "success") val success: Boolean,
    @SerializedName(value = "expires_at") val expiresAt: Date,
    @SerializedName(value = "request_token") val requestToken: String
)