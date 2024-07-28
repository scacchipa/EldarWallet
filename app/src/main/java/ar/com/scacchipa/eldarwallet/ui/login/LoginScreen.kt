package ar.com.scacchipa.eldarwallet.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.R
import ar.com.scacchipa.eldarwallet.ui.widgets.StandardTextField

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
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = data.isRegisterButtonEnabled,
            onClick = { viewModel.onRegisterButtonPushed() }) {
            Text(text = "Register")
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = data.isUnregisterButtonEnabled,
            onClick = { viewModel.onUnregisterButtonPushed() }) {
            Text("Unregister")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = data.isLoginButtonEnable,
            onClick = { viewModel.onLogInButtonPushed() }) {
            Text(text = "Log in")
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = data.isLogOutButtonEnable,
            onClick = { viewModel.onLogOutButtonPushed() }) {
            Text(text = "Log out")
        }
        Spacer(modifier = Modifier.height(40.dp))
        Switch(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            checked = data.isLogged,
            onCheckedChange = {
                viewModel.onSwitchTapped(it)
            }
        )
    }
}
