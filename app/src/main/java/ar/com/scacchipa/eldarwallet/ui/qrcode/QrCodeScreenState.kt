package ar.com.scacchipa.eldarwallet.ui.qrcode

import android.graphics.Bitmap

data class QrCodeScreenState(
    val qrCodeBitmap: Bitmap? = null,
    val isQrRefreshButtonEnabled: Boolean = false
)
