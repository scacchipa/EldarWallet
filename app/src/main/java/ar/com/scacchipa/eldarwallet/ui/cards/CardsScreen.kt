package ar.com.scacchipa.eldarwallet.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.R

@Composable
fun CardScreen(
    viewModel: CardsViewModel = hiltViewModel()
) {
    val data by viewModel.cardsScreenState.collectAsState()

    if (data.isUserLogged) {
        Column {
            OutlinedTextField(
                enabled = false,
                value = data.owner,
                onValueChange = { owner -> viewModel.onCodeChanged(owner) },
                label = { Text(text = "Owner") },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(width = 32.dp, height = 32.dp),
                        painter = painterResource(id = R.drawable.person_fill_svgrepo_com),
                        contentDescription = "User name"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = data.cardNumber,
                onValueChange = { cardNumber -> viewModel.onCardNumberChanged(cardNumber) },
                label = { Text(text = "Card Number") },
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(width = 32.dp, height = 32.dp),
                        painter = painterResource(
                            id = when (data.cardNumber.firstOrNull()) {
                                '3' -> R.drawable.amex_svgrepo_com
                                '4' -> R.drawable.visa_4_logo_svgrepo_com
                                '5' -> R.drawable.master_card_svgrepo_com
                                else -> R.drawable.credit_card_left_svgrepo_com
                            }
                        ),
                        contentDescription = "Card number"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = data.cvv,
                onValueChange = { cvv -> viewModel.onCvvChanged(cvv) },
                label = { Text(text = "CVV") },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(width = 32.dp, height = 32.dp),
                        painter = painterResource(id = R.drawable.password_svgrepo_com),
                        contentDescription = "Cvv Icon"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = data.dueDate,
                onValueChange = { dueDate -> viewModel.onDueDateChanged(dueDate) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(width = 32.dp, height = 32.dp),
                        painter = painterResource(id = R.drawable.data_element_infographic_deadline_time_graph_svgrepo_com),
                        contentDescription = "Due Date Icon"
                    )
                },
                label = { Text(text = "Due Date") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { viewModel.onAppendButtonPushed() }) {
                Text("Save new credit card")
            }

            LazyColumn {
                items(data.list) {
                    CreditCard(
                        cardNumber = it.cardNumber,
                        cardHolder = it.ownerName,
                        expiryDate = it.dueDate,
                        cvv = it.cvv
                    )
                }
            }
        }
    }
}

