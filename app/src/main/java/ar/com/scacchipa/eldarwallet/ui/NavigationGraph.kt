package ar.com.scacchipa.eldarwallet.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = LoginRoute.name,
        modifier = modifier
    ) {
        composable(LoginRoute.name) { LoginScreen() }
        composable(MainRoute.name) { MainScreen() }
        composable(CardRoute.name) { CardScreen() }
        composable(QRCodeRoute.name) { QRCodeScreen() }
        composable(PayRoute.name) { PayScreen() }
    }
}