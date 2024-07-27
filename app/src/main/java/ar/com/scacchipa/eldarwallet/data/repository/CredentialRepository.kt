package ar.com.scacchipa.eldarwallet.data.repository

import ar.com.scacchipa.eldarwallet.data.sourcedata.SharedPrefManager
import ar.com.scacchipa.eldarwallet.util.Constants.Companion.FAMILY_NAME_IDENTIFIER
import ar.com.scacchipa.eldarwallet.util.Constants.Companion.FIRST_NAME_IDENTIFIER
import ar.com.scacchipa.eldarwallet.util.Constants.Companion.PASSWORD_IDENTIFIER
import ar.com.scacchipa.eldarwallet.util.Constants.Companion.USER_NAME_IDENTIFIER
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialRepository @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) {
    private var isUserLogged = false

    private val _mutableCredentialStatusStateFlow = MutableStateFlow(
        CredentialStatus(
            isUserLogged = false,
            isUserRegistered = this.isUserRegistered()
        )
    )

    val credentialStatusStateFlow: StateFlow<CredentialStatus> = _mutableCredentialStatusStateFlow

    fun storeFistName(firstName: String) {
        sharedPrefManager.put(FIRST_NAME_IDENTIFIER, firstName)
        emitCredentialStatus()
    }

    fun getFirstName(): String {
        return sharedPrefManager.getString(FIRST_NAME_IDENTIFIER)
    }

    fun storeFamilyName(familyName: String) {
        sharedPrefManager.put(FAMILY_NAME_IDENTIFIER, familyName)
        emitCredentialStatus()
    }

    fun getFamilyName(): String {
        return sharedPrefManager.getString(FAMILY_NAME_IDENTIFIER)
    }

    fun storeUserName(userName: String) {
        sharedPrefManager.put(USER_NAME_IDENTIFIER, userName)
        emitCredentialStatus()
    }

    fun getUserName(): String {
        return sharedPrefManager.getString(USER_NAME_IDENTIFIER)
    }

    fun storePassword(password: String) {
        sharedPrefManager.put(PASSWORD_IDENTIFIER, password)
        emitCredentialStatus()
    }

    fun getPassword(): String {
        return sharedPrefManager.getString(PASSWORD_IDENTIFIER)
    }

    fun removeCredentials() {
        sharedPrefManager.remove(USER_NAME_IDENTIFIER)
        sharedPrefManager.remove(PASSWORD_IDENTIFIER)
        isUserLogged = false
        emitCredentialStatus()
    }

    fun isUserRegistered(): Boolean {
        return getUserName().isNotEmpty()
    }

    fun logIn() {
        isUserLogged = true
        emitCredentialStatus()
    }

    fun logOut() {
        isUserLogged = false
        emitCredentialStatus()
    }

    fun isUserLogged(): Boolean {
        return isUserLogged
    }

    private fun emitCredentialStatus() {
        _mutableCredentialStatusStateFlow.update {
            CredentialStatus(
                isUserRegistered = isUserRegistered(),
                isUserLogged = isUserLogged
            )
        }
    }
}
