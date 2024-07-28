package ar.com.scacchipa.eldarwallet.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.R
import ar.com.scacchipa.eldarwallet.ext.cardValidator
import ar.com.scacchipa.eldarwallet.ext.format

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val data by viewModel.mainStateFlow.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data.cards) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .fillParentMaxWidth(0.8f)
                    .border(2.dp, SolidColor(Color.Blue), shape = RoundedCornerShape(10.dp))
            ) {

                    Row {
                        Image(
                            modifier = Modifier
                                .size(width = 56.dp, height = 56.dp)
                                .padding(horizontal = 8.dp, vertical = 5.dp),

                            painter = painterResource(
                                id = it.cardNumber.cardValidator()?.drawableRes
                                    ?: R.drawable.credit_card_left_svgrepo_com
                            ),
                            contentDescription = "Credit Card"
                        )
                        Text(
                            fontSize = 24.sp,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = it.cardNumber)
                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                        fontSize = 24.sp,
                        text = "Balance: ${it.balance.format(2)}" )

            }
        }
    }
}
