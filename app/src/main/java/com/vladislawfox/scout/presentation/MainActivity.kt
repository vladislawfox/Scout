package com.vladislawfox.scout.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladislawfox.scout.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

   companion object {
       fun createIntent(context: Context): Intent {
           return Intent(context, MainActivity::class.java)
       }
   }
}