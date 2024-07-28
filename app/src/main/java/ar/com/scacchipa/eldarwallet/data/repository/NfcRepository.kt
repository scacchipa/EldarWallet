package ar.com.scacchipa.eldarwallet.data.repository

import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import javax.inject.Inject

class NfcRepository @Inject constructor() {
    suspend fun transfer(
        account: String,
        amount: Double,
        card: CardEntity
    ) : Boolean {
        return true
    }
}