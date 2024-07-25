package ar.com.scacchipa.eldarwallet.data.sourcedata

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val  sharedPref: SharedPreferences
) {

    fun getString(key: String) : String = sharedPref.getString(key, "") ?: ""

    fun put(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    fun remove(key: String) {
        sharedPref.edit().remove(key).apply()
    }
}
