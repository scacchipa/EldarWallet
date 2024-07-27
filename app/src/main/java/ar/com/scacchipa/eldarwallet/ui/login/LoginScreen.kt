package ar.com.scacchipa.eldarwallet.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.R
import ar.com.scacchipa.eldarwallet.ui.StandardTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val data by viewModel.loginStateFlow.collectAsState()
    Column {
        StandardTextField(
            value = data.firstName,
            onValueChange = viewModel::onFirstNameChanged,
            labelText = "First Name",
            drawableIconId = R.drawable.person_fill_svgrepo_com
        )
        StandardTextField(
            value = data.familyName,
            onValueChange = viewModel::onFamilyNameChanged,
            labelText = "Family Name",
            drawableIconId = R.drawable.person_fill_svgrepo_com)
        StandardTextField(
            value = data.userName,
            onValueChange = viewModel::onUserNameChanged,
            labelText = "User name",
            drawableIconId = R.drawable.person_fill_svgrepo_com,
        )
        StandardTextField(
            value = data.password,
            onValueChange = viewModel::onPasswordChanged,
            labelText = "User name",
            drawableIconId = R.drawable.password_svgrepo_com
        )
        Button(
            enabled = data.isRegisterButtonEnabled,
            onClick = { viewModel.onRegisterButtonPushed() }) {
            Text(text = "Register")
        }
        Button(
            enabled = data.isUnregisterButtonEnabled,
            onClick = { viewModel.onUnregisterButtonPushed() }) {
            Text("Unregister")
        }
        Button(
            enabled = data.isLoginButtonEnable,
            onClick = { viewModel.onLogInButtonPushed() }) {
            Text(text = "Log in")
        }
        Button(
            enabled = data.isLogOutButtonEnable,
            onClick = { viewModel.onLogOutButtonPushed() }) {
            Text(text = "Log out")
        }
        Switch(
            checked = data.isLogged,
            onCheckedChange = {
                viewModel.onSwitchTapped(it)
            }
        )
    }
}

