package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CardRepository
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllCard @Inject constructor(
    private val cardRepository: CardRepository,
    @DefaultDispatcher val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() : List<CardEntity> {
        return withContext(defaultDispatcher) {
            return@withContext cardRepository.getAll()
        }
    }
}
