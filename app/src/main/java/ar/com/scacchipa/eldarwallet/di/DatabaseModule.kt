package ar.com.scacchipa.eldarwallet.di

import android.content.Context
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardDao
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CardDataBase {
        SQLiteDatabase.loadLibs(context)
        val passphrase: ByteArray = SQLiteDatabase.getBytes("your_secure_passphrase".toCharArray())
        return CardDataBase.getDatabase(context, username, passphrase)
    }

    @Provides
    fun provideCardDao(database: CardDataBase): CardDao {
        return database.cardDao()
    }
}
