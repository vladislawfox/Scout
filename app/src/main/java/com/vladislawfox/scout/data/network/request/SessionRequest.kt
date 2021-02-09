package com.vladislawfox.scout.data.network.request

import com.google.gson.annotations.SerializedName

data class SessionRequest(
    @SerializedName("request_token") val requestToken: String
)