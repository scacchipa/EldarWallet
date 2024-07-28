package ar.com.scacchipa.eldarwallet.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.usecase.GetAllCard
import ar.com.scacchipa.eldarwallet.usecase.GetCredentialStatusFlow
import ar.com.scacchipa.eldarwallet.usecase.GetFamilyName
import ar.com.scacchipa.eldarwallet.usecase.GetFirstName
import ar.com.scacchipa.eldarwallet.usecase.GetUserName
import ar.com.scacchipa.eldarwallet.usecase.InsertCard
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
    private val getCredentialStatusFlow: GetCredentialStatusFlow,
    private val getUserName: GetUserName,
    private val insertCard: InsertCard,
    private val getAllCard: GetAllCard,
    private val getFirstName: GetFirstName,
    private val getFamilyName: GetFamilyName,
) : ViewModel() {

    private val onlyNumberRegex = Regex("^\\d+\$")

    private val _cardsScreenStateFlow = MutableStateFlow(CardsScreenState())
    val cardsScreenState: StateFlow<CardsScreenState> = _cardsScreenStateFlow

    private val _showToastStateFlow = MutableStateFlow<String?>(null)
    val showToastMessage: StateFlow<String?> = _showToastStateFlow

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

    fun onOwnerCodeChanged(owner: String) {
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
            if (cardNumber.isEmpty() || cardNumber.matches(onlyNumberRegex)) {
                _cardsScreenStateFlow.update {
                    it.copy(
                        cardNumber = cardNumber
                    )
                }
            }
        }
    }

    fun onCvvChanged(cvv: String) {
        viewModelScope.launch {
            if (cvv.isEmpty() || cvv.matches(onlyNumberRegex)) {
                _cardsScreenStateFlow.update {
                    it.copy(
                        cvv = cvv
                    )
                }
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
                dueDate = _cardsScreenStateFlow.value.dueDate,
                balance = 0.0
            )
            val wasInserted = insertCard(cardEntity)

            if (!wasInserted) {
                _showToastStateFlow.emit("INVALID CARD INFO")
            }

            refreshAllEntities()
        }
    }

    fun onToastShown() {
        viewModelScope.launch {
            _showToastStateFlow.emit(null)
        }
    }

    private suspend fun refreshAllEntities() {
        viewModelScope.launch {
            _cardsScreenStateFlow.update {
                it.copy(
                    owner = "${getFirstName()} ${getFamilyName()}",
                    list = getAllCard()
                )
            }
        }
    }
}
