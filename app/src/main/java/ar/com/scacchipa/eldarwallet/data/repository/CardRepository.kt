package ar.com.scacchipa.eldarwallet.data.repository

import android.content.Context
import ar.com.scacchipa.eldarwallet.data.sourcedata.CardValidatorService
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardDataBase
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
    private var firstName: String? = null
    private var familyName: String? = null

    private val _cardStateFlow = MutableStateFlow<List<CardEntity>>(listOf())
    val cardStateFlow: StateFlow<List<CardEntity>> = _cardStateFlow

    fun changeDatabase(
        firstName: String,
        familyName: String,
        userName: String,
        password: String
    ) {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes(password.toCharArray())
        this.cardDatabase = CardDataBase.getDatabase(context, userName, passphrase)
        this.userName = userName
        this.firstName = firstName
        this.familyName = familyName
    }

    suspend fun insert(cardEntity: CardEntity): Boolean {
        val firstName = this@CardRepository.firstName ?: return false
        val familyName = this@CardRepository.familyName ?: return false

        return withContext(ioDispatcher) {
            if (cardValidatorService.validateCardEntity(cardEntity, firstName, familyName)) {
                cardDatabase?.cardDao()?.insert(cardEntity)
                emitChanges()
                return@withContext true
            } else {
                return@withContext false
            }
        }
    }

    suspend fun getAll(): List<CardEntity> {
        return withContext(ioDispatcher) {
            return@withContext cardDatabase?.cardDao()?.getAll() ?: listOf()
        }
    }

    suspend fun updateCard(card: CardEntity, amount: Double): Boolean {
        val firstName = this@CardRepository.firstName ?: return false
        val familyName = this@CardRepository.familyName ?: return false

        if (cardValidatorService.validateCardEntity(card, firstName, familyName)) {
            return withContext(ioDispatcher) {
                val changed = (cardDatabase?.cardDao()?.updateCard(
                    card = card.copy(balance = amount)
                ) ?: 0) > 0

                emitChanges()
                return@withContext changed
            }
        } else {
            return false
        }
    }

    private suspend fun emitChanges() {
        _cardStateFlow.update {
            cardDatabase?.cardDao()?.getAll() ?: listOf()
        }
    }
}
