package ar.com.scacchipa.eldarwallet.data.sourcedata

import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import javax.inject.Inject

class CardValidatorService @Inject constructor() {
    fun validateCardEntity(entity: CardEntity, userName: String): Boolean {
        return entity.ownerName == userName
                && entity.cardNumber.firstOrNull() == entity.cvv.firstOrNull()
    }
}