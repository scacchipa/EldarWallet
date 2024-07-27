package ar.com.scacchipa.eldarwallet.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardDataBase
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.usecase.GetCredentialStatusFlow
import ar.com.scacchipa.eldarwallet.usecase.GetUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardDataBase: CardDataBase,
    private val getCredentialStatusFlow: GetCredentialStatusFlow,
    private val getUserName: GetUserName
) : ViewModel() {

    private val _cardsScreenStateFlow = MutableStateFlow(CardsScreenState())
    val cardsScreenState: StateFlow<CardsScreenState> = _cardsScreenStateFlow

    init {
        viewModelScope.launch {
            getCredentialStatusFlow()
                .onEach { credentialStatus ->
                    _cardsScreenStateFlow.update {
                        it.copy(
                            isUserLogged = credentialStatus.isUserLogged
                        )
                    }
                }
                .launchIn(viewModelScope)
            refreshAllEntities()
        }
    }

    fun onCodeChanged(owner: String) {
        viewModelScope.launch {
            _cardsScreenStateFlow.update {
                it.copy(
                    owner = owner
                )
            }
        }
    }

    fun onCardNumberChanged(cardNumber: String) {
        viewModelScope.launch {
            _cardsScreenStateFlow.update {
                it.copy(
                    cardNumber = cardNumber
                )
            }
        }
    }

    fun onCvvChanged(cvv: String) {
        viewModelScope.launch {
            _cardsScreenStateFlow.update {
                it.copy(
                    cvv = cvv
                )
            }
        }
    }

    fun onDueDateChanged(dueDate: String) {
        viewModelScope.launch {
            _cardsScreenStateFlow.update {
                it.copy(
                    dueDate = dueDate
                )
            }
        }
    }

    fun onAppendButtonPushed() {
        viewModelScope.launch {
            val cardEntity = CardEntity(
                ownerName = _cardsScreenStateFlow.value.owner,
                cardNumber = _cardsScreenStateFlow.value.cardNumber,
                cvv = _cardsScreenStateFlow.value.cvv,
                dueDate = _cardsScreenStateFlow.value.dueDate
            )
            cardDataBase.cardDao().insert(cardEntity)

            refreshAllEntities()
        }
    }

    private suspend fun refreshAllEntities() {
        val entities =
        _cardsScreenStateFlow.update {
            it.copy(
                owner = getUserName() ?: "",
                list = cardDataBase.cardDao().getAll()
            )
        }
    }
}

