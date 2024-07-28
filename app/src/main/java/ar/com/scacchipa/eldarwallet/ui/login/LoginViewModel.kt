package ar.com.scacchipa.eldarwallet.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.scacchipa.eldarwallet.usecase.GetCredentialStatusFlow
import ar.com.scacchipa.eldarwallet.usecase.LogInUser
import ar.com.scacchipa.eldarwallet.usecase.LogOutUser
import ar.com.scacchipa.eldarwallet.usecase.RegisterNewCredential
import ar.com.scacchipa.eldarwallet.usecase.RemoveCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val registerNewCredential: RegisterNewCredential,
    private val getCredentialStatusFlow: GetCredentialStatusFlow,
    private val removeCredentials: RemoveCredentials,
    private val logInUser: LogInUser,
    private val logOutUser: LogOutUser
) : ViewModel() {

    private val _loginStateFlow = MutableStateFlow(LoginScreenState())
    val loginStateFlow = _loginStateFlow as StateFlow<LoginScreenState>

    init {
        getCredentialStatusFlow()
            .onEach { credentialStatus ->
                _loginStateFlow.emit(
                    _loginStateFlow.value.copy(
                        isRegisterButtonEnabled = !credentialStatus.isUserRegistered,
                        isUnregisterButtonEnabled = credentialStatus.isUserRegistered,
                        isLoginButtonEnable = credentialStatus.isUserRegistered
                                && !credentialStatus.isUserLogged,
                        isLogOutButtonEnable = credentialStatus.isUserRegistered
                                && credentialStatus.isUserLogged,
                        isLogged = credentialStatus.isUserLogged
                    )
                )
            }
            .launchIn(viewModelScope)
    }

    fun onFirstNameChanged(newFirstName: String) {
        _loginStateFlow.update {
            it.copy(firstName = newFirstName)
        }
    }

    fun onFamilyNameChanged(newFamilyName: String) {
        _loginStateFlow.update {
            it.copy(familyName = newFamilyName)
        }
    }

    fun onUserNameChanged(newUserName: String) {
        _loginStateFlow.update {
            it.copy(userName = newUserName)
        }
    }

    fun onPasswordChanged(newPassword: String) {
        _loginStateFlow.update {
            it.copy(
                password = newPassword
            )
        }
    }

    fun onRegisterButtonPushed() {
        viewModelScope.launch {
            with(_loginStateFlow.value) {
                registerNewCredential(
                    firstName, familyName, userName, password
                )
            }
        }
    }

    fun onUnregisterButtonPushed() {
        viewModelScope.launch {
            with(_loginStateFlow.value) {
                removeCredentials(userName, password)
            }
        }
    }

    fun onLogInButtonPushed() {
        viewModelScope.launch {
            with(_loginStateFlow.value) {
                logInUser(userName, password)
            }
        }
    }

    fun onLogOutButtonPushed() {
        viewModelScope.launch {
            logOutUser()
        }
    }

    fun onSwitchTapped(isEnabled: Boolean) {
        if (isEnabled) {
            onLogInButtonPushed()
        } else {
            onLogOutButtonPushed()
        }
    }
}
