package ar.com.scacchipa.eldarwallet.ui

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import ar.com.scacchipa.eldarwallet.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar {
        IconButton(onClick = { navController.navigate(LoginRoute.name) }) {
            Icon(
                painter = painterResource(id = R.drawable.person_fill_svgrepo_com),
                contentDescription = "Login"
            )
        }
        IconButton(onClick = { navController.navigate(MainRoute.name) }) {
            Icon(
                painter = painterResource(id = R.drawable.home_svgrepo_com),
                contentDescription = "Main",
            )
        }
        IconButton(onClick = { navController.navigate(CardRoute.name) }) {
            Icon(
                painter = painterResource(id = R.drawable.card_svgrepo_com),
                contentDescription = "Card",
            )
        }
        IconButton(onClick = { navController.navigate(QRCodeRoute.name) }) {
            Icon(
                painter = painterResource(id = R.drawable.qr_scan_svgrepo_com),
                contentDescription = "QR Code",
            )
        }
        IconButton(onClick = { navController.navigate(PayRoute.name) }) {
            Icon(
                painter = painterResource(id = R.drawable.cash_payment_svgrepo_com),
                contentDescription = "Pay",
            )
        }
    }
}

sealed class Routes(val name: String)
object LoginRoute : Routes("Login")
object MainRoute : Routes("Main")
object CardRoute : Routes("Card")
object QRCodeRoute : Routes("QRCode")
object PayRoute : Routes("Pay")
