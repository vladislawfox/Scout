package com.vladislawfox.scout.domain

import com.vladislawfox.scout.base.usecase.UseCaseWithoutParams
import com.vladislawfox.scout.data.UserRepository
import com.vladislawfox.scout.data.local.EncryptPrefsManager
import com.vladislawfox.scout.data.network.request.SessionV4Request
import javax.inject.Inject

class GetSessionV4UseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val encryptPrefsManager: EncryptPrefsManager
) : UseCaseWithoutParams<Boolean>() {
    override suspend fun execute(): Boolean {
        val request = SessionV4Request(encryptPrefsManager.sessionId)
        return userRepository.getSessionV4(request)
    }
}