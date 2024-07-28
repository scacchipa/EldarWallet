package ar.com.scacchipa.eldarwallet.ui.cards

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.R
import ar.com.scacchipa.eldarwallet.ext.cardValidator
import ar.com.scacchipa.eldarwallet.ui.StandardTextField

@Composable
fun CardScreen(
    viewModel: CardsViewModel = hiltViewModel()
) {
    val data by viewModel.cardsScreenState.collectAsState()
    val context = LocalContext.current

    val showToastEvent by viewModel.showToastMessage.collectAsState()

    LaunchedEffect(showToastEvent) {
        showToastEvent?.let { text ->
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            viewModel.onToastShown()
        }
    }

    if (data.isUserLogged) {
        Column {
            StandardTextField(
                enabled = false,
                value = data.owner,
                onValueChange = { owner -> viewModel.onOwnerCodeChanged(owner) },
                labelText = "Owner",
                drawableIconId = R.drawable.person_fill_svgrepo_com
            )
            StandardTextField(
                value = data.cardNumber,
                onValueChange = { cardNumber -> viewModel.onCardNumberChanged(cardNumber) },
                labelText = "Card Number",
                drawableIconId = data.cardNumber.cardValidator()?.drawableRes
                    ?: R.drawable.credit_card_left_svgrepo_com
            )
            StandardTextField(
                value = data.cvv,
                onValueChange = { cvv -> viewModel.onCvvChanged(cvv) },
                labelText = "CVV",
                drawableIconId = R.drawable.password_svgrepo_com
            )
            StandardTextField(
                value = data.dueDate,
                onValueChange = { dueDate -> viewModel.onDueDateChanged(dueDate) },
                drawableIconId = R.drawable.data_element_infographic_deadline_time_graph_svgrepo_com,
                labelText = "Due Date"
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
