package com.vladislawfox.scout.domain

import com.vladislawfox.scout.base.usecase.UseCase
import com.vladislawfox.scout.data.UserRepository
import com.vladislawfox.scout.data.network.request.SessionRequest
import com.vladislawfox.scout.data.network.request.TokenWithLoginRequest
import javax.inject.Inject

class GetSessionWithLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCase<GetSessionWithLoginUseCase.Params, Boolean>() {

    override suspend fun execute(params: Params): Boolean {
        val token = userRepository.getRequestToken().requestToken
        val tokenWithLoginRequest = TokenWithLoginRequest(params.username, params.password, token)
        val approvedToken = userRepository.getSessionWithLogin(request = tokenWithLoginRequest).requestToken
        return userRepository.getSession(request = SessionRequest(approvedToken))
    }

    data class Params(val username: String, val password: String)
}