package com.vladislawfox.scout.data.network.request

import com.google.gson.annotations.SerializedName

data class SessionV4Request(
    @SerializedName("access_token")
    val accessToken: String
)
