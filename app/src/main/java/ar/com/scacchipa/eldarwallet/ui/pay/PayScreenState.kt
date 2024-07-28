package ar.com.scacchipa.eldarwallet.ui.pay

import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity

data class PayScreenState(
    val listCard: List<CardEntity> = listOf(),
    val itemSelected: CardEntity? = null,
    val destinationAccount: String = "",
    val amount: String = ""
)
