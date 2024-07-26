package ar.com.scacchipa.eldarwallet.data.repository

import ar.com.scacchipa.eldarwallet.data.sourcedata.QrCodeService
import ar.com.scacchipa.eldarwallet.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QrCodeRepository @Inject constructor(
    private val qrCodeService: QrCodeService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

) {
    suspend fun createQrCodeBytes(
        text: String,
        fill: String = "black",
        back: String = "white"
    ): ByteArray? = withContext(ioDispatcher) {
        try {
            val response = qrCodeService.fetchQrCodeImageBytes(text, fill, back)

            if (response.isSuccessful && response.code == 200) {
                return@withContext response.body?.bytes()
            } else {
                return@withContext null
            }

        } catch (e: Exception) {
            println(e)
            return@withContext null
        }
    }
}
