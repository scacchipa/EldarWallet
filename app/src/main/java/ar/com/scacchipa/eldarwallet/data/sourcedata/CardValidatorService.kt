package ar.com.scacchipa.eldarwallet.data.sourcedata

import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import javax.inject.Inject

class CardValidatorService @Inject constructor() {
    fun validateCardEntity(entity: CardEntity, firstName: String, familyName: String): Boolean {
        return entity.ownerName == "$firstName $familyName"
                && entity.cardNumber.firstOrNull() == entity.cvv.firstOrNull()
    }
}