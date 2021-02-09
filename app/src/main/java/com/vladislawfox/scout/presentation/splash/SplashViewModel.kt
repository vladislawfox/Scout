package com.vladislawfox.scout.presentation.splash

import com.vladislawfox.scout.base.ui.BaseViewModel
import com.vladislawfox.scout.domain.IsUserLoggedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isUserLoggedUseCase: IsUserLoggedUseCase
) : BaseViewModel() {

    val isUserLogged: Flow<Boolean>
        get() = flow { emit(isUserLoggedUseCase.invoke()) }

}