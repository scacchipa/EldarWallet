package ar.com.scacchipa.eldarwallet.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ar.com.scacchipa.eldarwallet.data.repository.QrCodeRepository
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateQrImage @Inject constructor(
    private val repository: QrCodeRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(txt: String): Bitmap? {
        return withContext(defaultDispatcher) {

            val bytes = repository.createQrCodeBytes(text = txt)
                ?: return@withContext null

            return@withContext BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
    }
}
