package ar.com.scacchipa.eldarwallet.ui

import androidx.annotation.DrawableRes
import ar.com.scacchipa.eldarwallet.R

sealed class CardBranch(
    val name: String,
    @DrawableRes val drawableRes: Int
)

data object AmericanExpress : CardBranch(
    name = "AMERICAN EXPRESS",
    drawableRes = R.drawable.amex_svgrepo_com
)

data object GenericCard : CardBranch(
    name = "CARDHOLDER",
    drawableRes = R.drawable.credit_card_left_svgrepo_com
)

data object MasterCard : CardBranch(
    name = "MASTERCARD",
    drawableRes = R.drawable.master_card_svgrepo_com
)

data object Visa : CardBranch(
    name = "VISA",
    drawableRes = R.drawable.visa_4_logo_svgrepo_com
)