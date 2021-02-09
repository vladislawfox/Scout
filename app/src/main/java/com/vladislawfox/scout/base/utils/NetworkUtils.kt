package com.vladislawfox.scout.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        return when (connectivityManager) {
            null -> false
            else -> connectivityManager.activeNetwork
                ?.let { activeNetwork -> connectivityManager.getNetworkCapabilities(activeNetwork) }
                ?.let { capabilities -> isNetworkConnected(capabilities) }
                ?: false
        }
    }

    private fun isNetworkConnected(capabilities: NetworkCapabilities): Boolean {
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }
}