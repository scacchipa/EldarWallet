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
    private val qrCodeRepository: QrCodeRepository,
    private val getFirstName: GetFirstName,
    private val getFamilyName: GetFamilyName,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Bitmap? {
        return withContext(defaultDispatcher) {

            val bytes = qrCodeRepository.createQrCodeBytes(
                text = "${getFirstName()} ${getFamilyName()}")
                ?: return@withContext null

            return@withContext BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
    }
}
