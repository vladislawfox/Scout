package com.vladislawfox.scout.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vladislawfox.scout.base.extentions.collect
import com.vladislawfox.scout.presentation.MainActivity
import com.vladislawfox.scout.presentation.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isUserLogged.collect(this) { isUserLogged ->
            val intent = if (isUserLogged) {
                MainActivity.createIntent(this@SplashActivity)
            } else {
                AuthActivity.createIntent(this@SplashActivity)
            }
            startActivity(intent)
            finish()
        }
    }
}