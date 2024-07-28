package ar.com.scacchipa.eldarwallet.ui.pay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.usecase.GetAllCard
import ar.com.scacchipa.eldarwallet.usecase.GetCardListFlow
import ar.com.scacchipa.eldarwallet.usecase.TransferAmount
import ar.com.scacchipa.eldarwallet.util.Constants.Companion.ONLY_NUMBER_REGEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayScreenViewModel @Inject constructor(
    private val getAllCard: GetAllCard,
    private val transferAmount: TransferAmount,
    private val getCardListFlow: GetCardListFlow
    ) : ViewModel() {

    private val _payScreenStateFlow = MutableStateFlow(PayScreenState())
    val payScreenStateFlow: StateFlow<PayScreenState> = _payScreenStateFlow

    init {
        viewModelScope.launch {
            _payScreenStateFlow.update {
                it.copy(
                    listCard = getAllCard()
                )
            }
        }
        getCardListFlow()
            .onEach { cardList ->
                _payScreenStateFlow.update {
                    it.copy(
                        listCard = cardList
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onItemSelected(item: CardEntity) {
        _payScreenStateFlow.update {
            it.copy(itemSelected = item)
        }
    }

    fun onDestinationAccountChanged(account: String) {
        _payScreenStateFlow.update {
            it.copy(destinationAccount = account)
        }
    }

    fun onAmountChanged(amount: String) {
        if (amount.isEmpty() || amount.matches(ONLY_NUMBER_REGEX)) {
            _payScreenStateFlow.update {
                it.copy(
                    amount = amount
                )
            }
        }
    }

    fun onPayButtonTapped() {
        with(_payScreenStateFlow.value) {
            itemSelected?.let { selectedCardEntity ->
                viewModelScope.launch {
                    transferAmount(
                        amountTxt = amount,
                        account = destinationAccount,
                        card = selectedCardEntity
                    )
                }
            }
        }
    }
}
