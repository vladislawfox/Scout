package com.vladislawfox.scout.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.vladislawfox.scout.base.delegator.value
import com.vladislawfox.scout.base.utils.GlobalExceptionLogger
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptPrefsManager @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences = run {
        // creating EncryptedSharedPreferences can throw an exception. And in this case use regular shared preferences
        createEncryptedSharedPreferences(context)
            ?: context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }

    var sessionId: String by prefs.value(PREF_SESSION_ID, "")
    var sessionIdV4: String by prefs.value(PREF_SESSION_ID_V4, "")

    fun clear() {
        prefs.edit { clear() }
    }

    private fun createEncryptedSharedPreferences(context: Context): SharedPreferences? {
        return try {
            EncryptedSharedPreferences.create(
                PREFS_FILENAME,
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (ex: Exception) {
            GlobalExceptionLogger.logException(ex)
            null
        }
    }

    companion object {
        private const val PREFS_FILENAME = "prefs_encrypt_scout"
        private const val PREF_SESSION_ID = "session_id"
        private const val PREF_SESSION_ID_V4 = "session_id_v4"
    }
}