package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CredentialRepository
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogOutUser @Inject constructor(
    private val credentialRepository: CredentialRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() {
        withContext(defaultDispatcher) {
            if (credentialRepository.isUserLogged()) {
                credentialRepository.logOut()
            }
        }
    }
}
