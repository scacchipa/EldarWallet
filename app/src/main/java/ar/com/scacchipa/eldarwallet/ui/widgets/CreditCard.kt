package ar.com.scacchipa.eldarwallet.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.com.scacchipa.eldarwallet.R
import ar.com.scacchipa.eldarwallet.data.sourcedata.carddatabase.CardEntity
import ar.com.scacchipa.eldarwallet.ext.cardValidator
import ar.com.scacchipa.eldarwallet.ext.format

@Composable
fun CreditCard(
    card: CardEntity,
    borderColor: Color = Color.Blue,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .fillMaxSize(0.8f)
            .border(2.dp, SolidColor(borderColor), shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .background(backgroundColor)
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(width = 56.dp, height = 56.dp)
                    .padding(horizontal = 8.dp, vertical = 5.dp),
                painter = painterResource(
                    id = card.cardNumber.cardValidator()?.drawableRes
                        ?: R.drawable.credit_card_left_svgrepo_com
                ),
                contentDescription = "Credit Card"
            )
            Text(
                fontSize = 24.sp,
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                text = card.cardNumber
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
            fontSize = 24.sp,
            text = "Balance: ${card.balance.format(2)}"
        )
    }
}
