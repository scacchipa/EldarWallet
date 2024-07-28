package ar.com.scacchipa.eldarwallet.ui.main

import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity

data class MainScreenState(
    val cards: List<CardEntity> = listOf()
)
