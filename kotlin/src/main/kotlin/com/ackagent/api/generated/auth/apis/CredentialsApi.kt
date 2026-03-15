package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.EnrollCredentialRequest
import com.ackagent.api.generated.auth.models.EnrollCredentialResponse
import com.ackagent.api.generated.auth.models.IssuerPublicKeyInfo
import com.ackagent.api.generated.auth.models.RefreshCredentialRequest

interface CredentialsApi {
    /**
     * POST api/v1/credentials/enroll
     * Enroll a BBS+ anonymous attestation credential
     * Enrolls a new BBS+ credential with pseudonym support for a device. The device provides a blind commitment (with ZK proof) over its nym secret. The auth service verifies the device&#39;s attestation and binding signature, then requests a blind signature from the credential-issuer service.
     * Responses:
     *  - 200: Credential enrolled successfully.
     *  - 400: Invalid request (bad fields, binding signature invalid).
     *  - 409: Active credential already exists for this device.
     *  - 500: Internal server error.
     *
     * @param enrollCredentialRequest 
     * @return [EnrollCredentialResponse]
     */
    @POST("api/v1/credentials/enroll")
    suspend fun credentialsEnroll(@Body enrollCredentialRequest: EnrollCredentialRequest): Response<EnrollCredentialResponse>

    /**
     * GET api/v1/credentials/public-key
     * Get issuer public keys
     * Returns issuer public keys for BBS+ credential verification. Proxies to the credential-issuer service. No authentication required.
     * Responses:
     *  - 200: Issuer public keys.
     *
     * @return [kotlin.collections.List<IssuerPublicKeyInfo>]
     */
    @GET("api/v1/credentials/public-key")
    suspend fun credentialsGetPublicKey(): Response<kotlin.collections.List<IssuerPublicKeyInfo>>

    /**
     * POST api/v1/credentials/refresh
     * Refresh a BBS+ anonymous attestation credential
     * Refreshes an expiring credential. The device provides a new blind commitment (with ZK proof). The auth service revokes the old credential and issues a new one.
     * Responses:
     *  - 200: Credential refreshed successfully.
     *  - 400: Invalid request.
     *  - 403: Device attestation or authorization policy forbids credential refresh.
     *  - 404: No active credential found to refresh.
     *  - 500: Internal server error.
     *
     * @param refreshCredentialRequest 
     * @return [EnrollCredentialResponse]
     */
    @POST("api/v1/credentials/refresh")
    suspend fun credentialsRefresh(@Body refreshCredentialRequest: RefreshCredentialRequest): Response<EnrollCredentialResponse>

}
