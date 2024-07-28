package ar.com.scacchipa.eldarwallet.ui.pay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.ui.widgets.CreditCard
import ar.com.scacchipa.eldarwallet.ui.widgets.StandardTextField

@Composable
fun PayScreen(
    viewModel: PayScreenViewModel = hiltViewModel()
) {

    val data by viewModel.payScreenStateFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StandardTextField(
            value = data.destinationAccount,
            onValueChange = { account -> viewModel.onDestinationAccountChanged(account)},
            labelText = "Destination Account",
            drawableIconId = null)
        StandardTextField(
            value = data.amount,
            onValueChange = { amount -> viewModel.onAmountChanged(amount)},
            labelText = "Amount",
            drawableIconId = null)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(data.listCard) {
                CreditCard(
                    card = it,
                    onClick = { viewModel.onItemSelected(it) },
                    borderColor =
                    if (it == data.itemSelected) Color.Black
                    else Color.Blue,
                    backgroundColor =
                    if (it == data.itemSelected) Color.LightGray
                    else Color.White
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(0.80f)
                .padding(horizontal = 20.dp, vertical = 32.dp),

            onClick = viewModel::onPayButtonTapped
        ) {
            Text(
                fontSize = 24.sp,
                text = "Pay")
        }
    }
}
