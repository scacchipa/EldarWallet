package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CardRepository
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardListFlow @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(): Flow<List<CardEntity>> {
        return cardRepository.cardStateFlow
    }
}
