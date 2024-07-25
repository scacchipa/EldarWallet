package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CredentialRepository
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoveCredentials @Inject constructor(
    private val credentialRepository: CredentialRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(username: String, password: String) {
        withContext(defaultDispatcher) {
            with(credentialRepository) {
                if (isUserRegistered()
                    && getUserName() == username
                    && getPassword() == password
                    && isUserRegistered()) {
                        removeCredentials()
                    }
            }
        }
    }
}
