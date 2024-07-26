package ar.com.scacchipa.eldarwallet.ui.qrcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.scacchipa.eldarwallet.usecase.CreateQrImage
import ar.com.scacchipa.eldarwallet.usecase.GetCredentialStatusFlow
import ar.com.scacchipa.eldarwallet.usecase.GetUserName
import ar.com.scacchipa.eldarwallet.usecase.IsLogInUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrCodeViewModel @Inject constructor(
    private val createQrImage: CreateQrImage,
    private val getCredentialStatusFlow: GetCredentialStatusFlow,
    private val isLogInUser: IsLogInUser,
    private val getUserName: GetUserName
) : ViewModel() {

    private val _qrCodeImageStateFlow = MutableStateFlow(QrCodeScreenState())
    val qrCodeImageStateFlow: StateFlow<QrCodeScreenState?> = _qrCodeImageStateFlow

    init {
        getCredentialStatusFlow().onEach { credentialState ->
            _qrCodeImageStateFlow.update {
                it.copy(
                    isQrRefreshButtonEnabled = !credentialState.isUserLogged
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onPushGetQrCode() {
        viewModelScope.launch {
            if (isLogInUser()) {
                getUserName()?.let { userName ->
                    _qrCodeImageStateFlow.emit(
                        _qrCodeImageStateFlow.value.copy(
                            qrCodeBitmap = createQrImage(txt = userName)
                        )
                    )
                }
            }
        }
    }
}
