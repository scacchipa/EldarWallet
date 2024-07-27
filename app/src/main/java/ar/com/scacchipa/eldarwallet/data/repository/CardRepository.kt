package ar.com.scacchipa.eldarwallet.data.repository

import android.content.Context
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardDataBase
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import net.sqlcipher.database.SQLiteDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {

    private var cardDatabase: CardDataBase? = null

    fun changeDatabase(userName:  String, password: String) {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes(password.toCharArray())
        cardDatabase=  CardDataBase.getDatabase(context, userName, passphrase)
    }

    suspend fun insert(cardEntity: CardEntity) {
        withContext(ioDispatcher) {
            cardDatabase?.cardDao()?.insert(cardEntity)
        }
    }

    suspend fun getAll() : List<CardEntity> {
        return withContext(ioDispatcher) {
            return@withContext cardDatabase?.cardDao()?.getAll() ?: listOf()
        }
    }
}
