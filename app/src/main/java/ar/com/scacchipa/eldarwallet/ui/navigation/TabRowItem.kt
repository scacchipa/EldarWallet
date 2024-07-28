package ar.com.scacchipa.eldarwallet.ui.navigation

import androidx.compose.runtime.Composable
import ar.com.scacchipa.eldarwallet.R
import ar.com.scacchipa.eldarwallet.ui.cards.CardScreen
import ar.com.scacchipa.eldarwallet.ui.main.MainScreen
import ar.com.scacchipa.eldarwallet.ui.pay.PayScreen
import ar.com.scacchipa.eldarwallet.ui.login.LoginScreen
import ar.com.scacchipa.eldarwallet.ui.qrcode.QrCodeScreen

data class TabRowItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit,
)

val tabRowItems = listOf(
    TabRowItem(
        title = "Login",
        screen = { LoginScreen() },
        icon = R.drawable.person_fill_svgrepo_com,
    ), TabRowItem(
        title = "Main",
        screen = { MainScreen() },
        icon = R.drawable.home_svgrepo_com,
    ), TabRowItem(
        title = "Card",
        screen = { CardScreen() },
        icon = R.drawable.card_svgrepo_com,
    ), TabRowItem(
        title = "QR Code",
        screen = { QrCodeScreen() },
        icon = R.drawable.qr_scan_svgrepo_com,
    ), TabRowItem(
        title = "Pay",
        screen = { PayScreen() },
        icon = R.drawable.cash_payment_svgrepo_com,
    )
)
