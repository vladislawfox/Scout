package com.vladislawfox.scout.domain

import com.vladislawfox.scout.base.usecase.UseCaseWithoutParamsSync
import com.vladislawfox.scout.data.local.EncryptPrefsManager
import javax.inject.Inject

class IsUserLoggedUseCase @Inject constructor(private val prefsManager: EncryptPrefsManager) :
    UseCaseWithoutParamsSync<Boolean> {
    override fun invoke(): Boolean {
        return prefsManager.sessionId.isNotEmpty()
    }
}