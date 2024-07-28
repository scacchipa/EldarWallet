package ar.com.scacchipa.eldarwallet.ui.navigation

import androidx.lifecycle.ViewModel
import ar.com.scacchipa.eldarwallet.data.repository.CredentialStatus
import ar.com.scacchipa.eldarwallet.usecase.GetCredentialStatusFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val getCredentialStatusFlow: GetCredentialStatusFlow
) : ViewModel() {
    fun getCredStatusFlow(): StateFlow<CredentialStatus> {
        return getCredentialStatusFlow()
    }
}
