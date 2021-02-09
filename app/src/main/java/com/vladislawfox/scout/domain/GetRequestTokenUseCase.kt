package com.vladislawfox.scout.domain

import com.vladislawfox.scout.base.usecase.UseCaseWithoutParams
import com.vladislawfox.scout.data.UserRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(private val userRepository: UserRepository) :
    UseCaseWithoutParams<String>() {
    override suspend fun execute(): String {
        return userRepository.getRequestToken().requestToken
    }
}