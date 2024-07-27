package ar.com.scacchipa.eldarwallet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.sqlcipher.database.SQLiteDatabase

@HiltAndroidApp
class WalletEldarApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        SQLiteDatabase.loadLibs(this)
    }
}