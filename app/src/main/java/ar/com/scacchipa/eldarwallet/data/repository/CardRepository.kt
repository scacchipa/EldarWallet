package ar.com.scacchipa.eldarwallet.data.repository

import android.content.Context
import ar.com.scacchipa.eldarwallet.data.sourcedata.CardValidatorService
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
    private val cardValidatorService: CardValidatorService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private var cardDatabase: CardDataBase? = null
    private var userName: String? = null

    fun changeDatabase(userName:  String, password: String) {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes(password.toCharArray())
        cardDatabase =  CardDataBase.getDatabase(context, userName, passphrase)
        this.userName = userName
    }

    suspend fun insert(cardEntity: CardEntity): Boolean {
        return withContext(ioDispatcher) {
            userName?.let {
                if (cardValidatorService.validateCardEntity(cardEntity, it)) {
                    cardDatabase?.cardDao()?.insert(cardEntity)
                    return@let true
                } else {
                    return@let false
                }
            }?: return@withContext false
        }
    }

    suspend fun getAll() : List<CardEntity> {
        return withContext(ioDispatcher) {
            return@withContext cardDatabase?.cardDao()?.getAll() ?: listOf()
        }
    }
}
