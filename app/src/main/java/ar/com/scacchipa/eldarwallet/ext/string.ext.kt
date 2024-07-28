package ar.com.scacchipa.eldarwallet.ext

import ar.com.scacchipa.eldarwallet.ui.AmericanExpress
import ar.com.scacchipa.eldarwallet.ui.CardBranch
import ar.com.scacchipa.eldarwallet.ui.GenericCard
import ar.com.scacchipa.eldarwallet.ui.MasterCard
import ar.com.scacchipa.eldarwallet.ui.Visa

fun String.cardValidator(): CardBranch? {
    return if (this.isBlank()) null
    else when (this.firstOrNull()) {
        '3' -> AmericanExpress
        '4' -> Visa
        '5' -> MasterCard
        else -> GenericCard
    }
}

