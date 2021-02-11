package com.vladislawfox.scout.data.repositories

import com.vladislawfox.scout.data.local.EncryptPrefsManager
import com.vladislawfox.scout.data.network.services.UserService
import com.vladislawfox.scout.data.network.model.auth.TokenApiModel
import com.vladislawfox.scout.data.network.request.DeleteSessionRequest
import com.vladislawfox.scout.data.network.request.SessionRequest
import com.vladislawfox.scout.data.network.request.SessionV4Request
import com.vladislawfox.scout.data.network.request.TokenWithLoginRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService,
    private val encryptPrefsManager: EncryptPrefsManager
) {

    suspend fun getRequestToken(): TokenApiModel {
        return userService.getRequestToken()
    }

    suspend fun getSession(request: SessionRequest): Boolean {
        val session = userService.getSession(request)
        if (session.success) {
            encryptPrefsManager.sessionId = session.sessionId
        }
        return session.success
    }

    suspend fun getSessionWithLogin(request: TokenWithLoginRequest): TokenApiModel {
        return userService.getSessionWithLogin(request)
    }

    suspend fun getSessionV4(request: SessionV4Request): Boolean {
        val session = userService.getSessionV4(request)
        if (session.success) {
            encryptPrefsManager.sessionIdV4 = session.sessionId
        }
        return session.success
    }

    suspend fun deleteSession(request: DeleteSessionRequest) {
        userService.deleteSession(request)
    }

}