package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CardRepository
import ar.com.scacchipa.eldarwallet.data.repository.CredentialRepository
import ar.com.scacchipa.eldarwallet.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogInUser @Inject constructor(
    private val credentialRepository: CredentialRepository,
    private val cardRepository: CardRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        userName: String,
        password: String
    ) {
        withContext(defaultDispatcher) {
            if (credentialRepository.isUserRegistered()
                && credentialRepository.getUserName() == userName
                && credentialRepository.getPassword() == password
            ) {
                credentialRepository.logIn()
                cardRepository.changeDatabase(
                    firstName = credentialRepository.getFirstName(),
                    familyName = credentialRepository.getFamilyName(),
                    userName = userName,
                    password = password
                )
            }
        }
    }
}
