package ar.com.scacchipa.eldarwallet.ui.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.ui.widgets.CreditCard

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val data by viewModel.mainStateFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            fontSize = 32.sp,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .border(4.dp, SolidColor(Color.Blue), shape = RoundedCornerShape(20.dp))
                .padding(horizontal = 32.dp, vertical = 20.dp),
            text = "Accounts"
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(data.cards) {
                CreditCard(card = it)
            }
        }
    }
}
