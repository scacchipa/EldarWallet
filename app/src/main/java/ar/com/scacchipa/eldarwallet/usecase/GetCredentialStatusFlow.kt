package ar.com.scacchipa.eldarwallet.usecase

import ar.com.scacchipa.eldarwallet.data.repository.CredentialRepository
import ar.com.scacchipa.eldarwallet.data.repository.CredentialStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCredentialStatusFlow @Inject constructor(
    private val credentialRepository: CredentialRepository
) {
    operator fun invoke(): Flow<CredentialStatus> {
        return credentialRepository.credentialStatusStateFlow
    }
}
