package com.vladislawfox.scout.base.utils

import android.content.Intent

object AuthUtils {
    fun getAuthLink(requestToken: String): String {
        return "https://www.themoviedb.org/authenticate/$requestToken?redirect_to=scout://auth-approved"
    }

    fun getRequestToken(intent: Intent): String? {
        val data = intent.data ?: return null
        val token = data.getQueryParameter("request_token")
        val isApproved = data.getBooleanQueryParameter("approved", false)
        return if (isApproved) token else null
    }
}