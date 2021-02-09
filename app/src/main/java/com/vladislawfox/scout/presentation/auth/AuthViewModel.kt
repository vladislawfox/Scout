package com.vladislawfox.scout.presentation.auth

import com.vladislawfox.scout.base.ui.BaseViewModel
import com.vladislawfox.scout.domain.GetRequestTokenUseCase
import com.vladislawfox.scout.domain.GetSessionUseCase
import com.vladislawfox.scout.domain.GetSessionV4UseCase
import com.vladislawfox.scout.domain.GetSessionWithLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
        private val getRequestTokenUseCase: GetRequestTokenUseCase,
        private val getSessionUseCase: GetSessionUseCase,
        private val getSessionWithLoginUseCase: GetSessionWithLoginUseCase
) : BaseViewModel() {

    private val _userLogged = MutableStateFlow(value = false)
    val userLogged: StateFlow<Boolean>
        get() = _userLogged

    private val _requestToken = MutableStateFlow(value = "")
    val requestToken: StateFlow<String>
        get() = _requestToken

    fun loginWithUsername(username: String, password: String) = runCoroutine(withProgress = true) {
        val params = GetSessionWithLoginUseCase.Params(username, password)
        _userLogged.value = getSessionWithLoginUseCase.invoke(params)
    }

    fun login(requestToken: String) = runCoroutine(withProgress = true) {
        _userLogged.value = getSessionUseCase.invoke(requestToken)
    }

    fun requestToken() = runCoroutine(withProgress = true) {
        _requestToken.value = getRequestTokenUseCase.invoke()
    }
}