package ar.com.scacchipa.eldarwallet.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.scacchipa.eldarwallet.usecase.GetAllCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCard: GetAllCard
) : ViewModel() {

    private val _mainStateFlow = MutableStateFlow(MainScreenState())
    val mainStateFlow: StateFlow<MainScreenState> = _mainStateFlow

    init {
        viewModelScope.launch {
            _mainStateFlow.update {
                it.copy(cards = getAllCard())
            }
        }
    }
}
