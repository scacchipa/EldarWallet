package ar.com.scacchipa.eldarwallet.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val data by viewModel.loginStateFlow.collectAsState()
    Column {
        OutlinedTextField(
            value = data.userName,
            onValueChange = { userName -> viewModel.onUserNameChanged(userName) },
            label = { Text(text = "User name") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.person_fill_svgrepo_com),
                    contentDescription = "User name")
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = data.password,
            onValueChange = { password -> viewModel.onPasswordChanged(password) },
            label = { Text(text = "User name") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.password_svgrepo_com),
                    contentDescription = "User name")
            },
            modifier = Modifier.fillMaxWidth()
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
                viewModel.onSwichTapped()
            }
        )
    }
}

