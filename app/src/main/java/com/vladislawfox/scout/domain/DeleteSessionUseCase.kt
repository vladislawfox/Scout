package com.vladislawfox.scout.domain

import com.vladislawfox.scout.base.usecase.UseCaseWithoutParams
import com.vladislawfox.scout.data.UserRepository
import com.vladislawfox.scout.data.local.EncryptPrefsManager
import com.vladislawfox.scout.data.network.request.DeleteSessionRequest
import javax.inject.Inject

class DeleteSessionUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val encryptPrefsManager: EncryptPrefsManager
) : UseCaseWithoutParams<Unit>() {
    override suspend fun execute() {
        val request = DeleteSessionRequest(encryptPrefsManager.sessionId)
        userRepository.deleteSession(request)
    }
}