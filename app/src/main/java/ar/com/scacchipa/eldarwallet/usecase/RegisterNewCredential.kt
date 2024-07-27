package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CredentialRepository
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterNewCredential @Inject constructor(
    private val credentialRepository: CredentialRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
     suspend operator fun invoke(
         firstName: String,
         familyName: String,
         userName: String,
         password: String) {
        withContext(defaultDispatcher) {
            if (credentialRepository.isUserRegistered().not()) {
                credentialRepository.apply {
                    storeFistName(firstName)
                    storeFamilyName(familyName)
                    storeUserName(userName)
                    storePassword(password)
                }
            }
        }
    }
}
