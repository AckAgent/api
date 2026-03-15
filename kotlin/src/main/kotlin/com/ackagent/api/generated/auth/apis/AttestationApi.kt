package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.AttestCheckResponse
import com.ackagent.api.generated.auth.models.GetAttestationResponse

interface AttestationApi {
    /**
     * GET api/v1/approvers/{approverId}/attestation
     * Get approver attestation
     * Get approver attestation
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param approverId Approver device UUID
     * @return [GetAttestationResponse]
     */
    @GET("api/v1/approvers/{approverId}/attestation")
    suspend fun approverAttestationGet(@Path("approverId") approverId: kotlin.String): Response<GetAttestationResponse>

    /**
     * GET api/v1/attest/check
     * Check attestation rate limit
     * Check attestation rate limit - check if attestation is allowed for the requester
     * Responses:
     *  - 200: The request has succeeded.
     *
     * @return [AttestCheckResponse]
     */
    @GET("api/v1/attest/check")
    suspend fun attestCheckCheck(): Response<AttestCheckResponse>

}
