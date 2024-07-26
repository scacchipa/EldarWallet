package ar.com.scacchipa.eldarwallet.ui.qrcode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.scacchipa.eldarwallet.R

@Composable
fun QrCodeScreen(
    viewModel: QrCodeViewModel = hiltViewModel()
) {
    val data by viewModel.qrCodeImageStateFlow.collectAsState()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            enabled = data?.isQrRefreshButtonEnabled?.not() ?: false,
            onClick = { viewModel.onPushGetQrCode() }) {
            Text(
                text = "Get QR Code",
                fontSize = 20.sp
                )
        }

        val bitmap = data?.qrCodeBitmap
        if (bitmap == null) {
            Image(
                painter = painterResource(id = R.drawable.no_image_svgrepo_com),
                modifier = Modifier.fillMaxSize(0.80f),
                contentDescription = "No qr code image"
            )
        } else {
            Image(
                modifier = Modifier.fillMaxSize(0.80f),
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "QR Code"
            )
        }
    }
}
