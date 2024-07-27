package ar.com.scacchipa.eldarwallet.ui.cards

import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity

data class CardsScreenState(
    val isUserLogged: Boolean = false,
    val owner: String = "",
    val cardNumber: String = "",
    val cvv: String = "",
    val dueDate: String = "",
    val list: List<CardEntity> = listOf()
)