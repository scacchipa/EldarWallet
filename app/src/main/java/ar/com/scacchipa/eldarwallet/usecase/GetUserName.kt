package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CredentialRepository
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserName @Inject constructor(
    val credentialRepository: CredentialRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): String? {
        return withContext(defaultDispatcher) {
            if (credentialRepository.isUserLogged()) {
                return@withContext credentialRepository.getUserName()
            } else {
                return@withContext null
            }
        }
    }
}
