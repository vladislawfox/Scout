package com.vladislawfox.scout.base.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.vladislawfox.scout.base.extentions.inflateVB
import com.vladislawfox.scout.base.utils.InsetsUtils.handleInsets

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
        private set

    protected abstract fun createBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding()
        setContentView(binding.root)

        handleIntent(intent)

        handleInsets(binding.root) { left, top, right, bottom ->
            handleInsets(left, top, right, bottom)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    open fun handleIntent(intent: Intent) {
    }

    protected open fun handleInsets(left: Int, top: Int, right: Int, bottom: Int) {
    }

    protected inline fun <reified T : ViewBinding> inflate(): T {
        return layoutInflater.inflateVB(null, null)
    }
}