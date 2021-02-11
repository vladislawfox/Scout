package com.vladislawfox.scout.data.network.model.auth

import com.google.gson.annotations.SerializedName

data class TokenApiModel(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String
)
