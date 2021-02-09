package com.vladislawfox.scout.data.network

import com.vladislawfox.scout.data.network.model.SessionApiModel
import com.vladislawfox.scout.data.network.model.TokenApiModel
import com.vladislawfox.scout.data.network.request.DeleteSessionRequest
import com.vladislawfox.scout.data.network.request.SessionRequest
import com.vladislawfox.scout.data.network.request.SessionV4Request
import com.vladislawfox.scout.data.network.request.TokenWithLoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface UserService {
    @GET("authentication/token/new")
    suspend fun getRequestToken(): TokenApiModel

    @POST("authentication/session/new")
    suspend fun getSession(@Body request: SessionRequest): SessionApiModel

    @POST("authentication/token/validate_with_login")
    suspend fun getSessionWithLogin(@Body request: TokenWithLoginRequest): TokenApiModel

    @POST("authentication/session/convert/4")
    suspend fun getSessionV4(@Body request: SessionV4Request): SessionApiModel

    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    suspend fun deleteSession(@Body request: DeleteSessionRequest): Response<Unit>
}