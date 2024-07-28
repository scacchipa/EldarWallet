package ar.com.scacchipa.eldarwallet.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.scacchipa.eldarwallet.usecase.GetAllCard
import ar.com.scacchipa.eldarwallet.usecase.GetCardListFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardListFlow: GetCardListFlow,
    private val getAllCard: GetAllCard
) : ViewModel() {

    private val _mainStateFlow = MutableStateFlow(MainScreenState())
    val mainStateFlow: StateFlow<MainScreenState> = _mainStateFlow

    init {
        viewModelScope.launch {
            getCardListFlow()
                .onEach { cardList ->
                    _mainStateFlow.update {
                        it.copy(
                            cards = cardList
                        )
                    }
                }
                .launchIn(viewModelScope)
            _mainStateFlow.update {
                it.copy(cards = getAllCard())
            }
        }
    }
}
