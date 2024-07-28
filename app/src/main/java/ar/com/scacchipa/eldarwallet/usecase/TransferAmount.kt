package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CardRepository
import ar.com.scacchipa.eldarwallet.data.repository.NfcRepository
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransferAmount @Inject constructor(
    private val nfcRepository: NfcRepository,
    private val cardRepository: CardRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(amountTxt: String, account: String, card: CardEntity) {
        withContext(defaultDispatcher) {

            val amount: Double = amountTxt.toDoubleOrNull() ?: return@withContext
            val wasTransferred = nfcRepository.transfer(account, amount, card)

            if (wasTransferred) {
                cardRepository.updateCard(card, card.balance - amount)
            }
        }
    }
}
