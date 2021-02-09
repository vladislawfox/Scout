package com.vladislawfox.scout.presentation.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.vladislawfox.scout.base.extentions.collect
import com.vladislawfox.scout.base.extentions.hideKeyboard
import com.vladislawfox.scout.base.ui.BaseActivity
import com.vladislawfox.scout.base.utils.AuthUtils
import com.vladislawfox.scout.base.utils.UrlUtils
import com.vladislawfox.scout.databinding.ActivityAuthBinding
import com.vladislawfox.scout.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun createBinding(): ActivityAuthBinding = inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToViewModel()
        setupUi()
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    override fun handleIntent(intent: Intent) {
        AuthUtils.getRequestToken(intent)?.let { viewModel.login(it) }
    }

    private fun setupUi() = with(binding) {
        btnLogin.setOnClickListener {
            val username = etUsername.text?.toString() ?: return@setOnClickListener
            val password = etPassword.text?.toString() ?: return@setOnClickListener
            hideKeyboard()
            viewModel.loginWithUsername(username, password)
        }

        btnLoginWithTmdb.setOnClickListener { viewModel.requestToken() }
    }

    private fun subscribeToViewModel() {
        viewModel.userLogged.collect(this) { isLogged ->
            if (isLogged) {
                startActivity(MainActivity.createIntent(this@AuthActivity))
                finish()
            }
        }

        viewModel.requestToken.collect(this) { token ->
            if (token.isEmpty()) return@collect
            UrlUtils.openUrl(this@AuthActivity, AuthUtils.getAuthLink(token))
        }

        viewModel.progress.collect(this) { progress ->
            binding.btnLogin.isClickable = !progress
            binding.btnLoginWithTmdb.isClickable = !progress
            binding.progress.isVisible = progress
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }
}