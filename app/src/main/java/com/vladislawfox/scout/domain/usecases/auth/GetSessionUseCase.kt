package com.vladislawfox.scout.domain.usecases.auth

import com.vladislawfox.scout.base.usecase.UseCase
import com.vladislawfox.scout.data.repositories.UserRepository
import com.vladislawfox.scout.data.network.request.SessionRequest
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(private val userRepository: UserRepository) :
    UseCase<String, Boolean>() {
    override suspend fun execute(params: String): Boolean {
        val request = SessionRequest(params)
        return userRepository.getSession(request)
    }
}