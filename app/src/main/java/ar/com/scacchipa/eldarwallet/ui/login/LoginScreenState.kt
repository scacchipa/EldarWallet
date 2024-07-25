package ar.com.scacchipa.eldarwallet.ui.login

data class LoginScreenState(
    val userName:String = "",
    val password:String = "",

    val isRegisterButtonEnabled: Boolean = false,
    val isUnregisterButtonEnabled: Boolean = false,
    val isLoginButtonEnable: Boolean = false,
    val isLogOutButtonEnable: Boolean = false,

    val isLogged: Boolean = false
)
