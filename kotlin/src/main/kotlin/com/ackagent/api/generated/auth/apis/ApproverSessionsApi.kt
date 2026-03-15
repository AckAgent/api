package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.ClaimApproverSessionRequest
import com.ackagent.api.generated.auth.models.ClaimApproverSessionResponse
import com.ackagent.api.generated.auth.models.CreateApproverSessionRequest
import com.ackagent.api.generated.auth.models.CreateApproverSessionResponse
import com.ackagent.api.generated.auth.models.GetApproverSessionStatusResponse
import com.ackagent.api.generated.auth.models.GetApproverSessionTokensResponse
import com.ackagent.api.generated.auth.models.ListUserApproverSessionsResponse
import com.ackagent.api.generated.auth.models.VerifyApproverSessionRequest
import com.ackagent.api.generated.auth.models.VerifyApproverSessionResponse

interface ApproverSessionsApi {
    /**
     * POST api/v1/approver-sessions/{id}/claim
     * Claim approver session
     * Claim approver session - existing device claims an approver session after scanning the QR code
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 404: The server cannot find the requested resource.
     *  - 410: Client error
     *
     * @param id Approver session identifier
     * @param claimApproverSessionRequest Request payload
     * @return [ClaimApproverSessionResponse]
     */
    @POST("api/v1/approver-sessions/{id}/claim")
    suspend fun approverSessionClaimClaim(@Path("id") id: kotlin.String, @Body claimApproverSessionRequest: ClaimApproverSessionRequest): Response<ClaimApproverSessionResponse>

    /**
     * POST api/v1/approver-sessions/{id}/reject
     * Reject SAS
     * Reject SAS - existing device rejects the SAS (mismatch or user declined)
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Approver session identifier
     * @return [Unit]
     */
    @POST("api/v1/approver-sessions/{id}/reject")
    suspend fun approverSessionRejectReject(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * GET api/v1/approver-sessions/{id}/status
     * Poll approver session status
     * Poll approver session status
     * Responses:
     *  - 200: The request has succeeded.
     *  - 404: The server cannot find the requested resource.
     *  - 410: Client error
     *
     * @param id Approver session identifier
     * @return [GetApproverSessionStatusResponse]
     */
    @GET("api/v1/approver-sessions/{id}/status")
    suspend fun approverSessionStatusGet(@Path("id") id: kotlin.String): Response<GetApproverSessionStatusResponse>

    /**
     * GET api/v1/approver-sessions/{id}/tokens
     * Get tokens after verification
     * Get tokens after verification - new device fetches OAuth tokens after SAS verification is complete
     * Responses:
     *  - 200: The request has succeeded.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Approver session identifier
     * @return [GetApproverSessionTokensResponse]
     */
    @GET("api/v1/approver-sessions/{id}/tokens")
    suspend fun approverSessionTokensGet(@Path("id") id: kotlin.String): Response<GetApproverSessionTokensResponse>

    /**
     * POST api/v1/approver-sessions/{id}/verify
     * Verify SAS match
     * Verify SAS match - existing device confirms the SAS displayed on new device matches
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Approver session identifier
     * @param verifyApproverSessionRequest Request payload
     * @return [VerifyApproverSessionResponse]
     */
    @POST("api/v1/approver-sessions/{id}/verify")
    suspend fun approverSessionVerifyVerify(@Path("id") id: kotlin.String, @Body verifyApproverSessionRequest: VerifyApproverSessionRequest): Response<VerifyApproverSessionResponse>

    /**
     * POST api/v1/approver-sessions
     * Create approver session
     * Create approver session
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *
     * @param createApproverSessionRequest Request payload
     * @return [CreateApproverSessionResponse]
     */
    @POST("api/v1/approver-sessions")
    suspend fun approverSessionsCreate(@Body createApproverSessionRequest: CreateApproverSessionRequest): Response<CreateApproverSessionResponse>

    /**
     * GET api/v1/users/{id}/approver-sessions
     * List pending approver sessions
     * List pending approver sessions - existing devices poll this to see pending device link sessions
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @return [ListUserApproverSessionsResponse]
     */
    @GET("api/v1/users/{id}/approver-sessions")
    suspend fun userApproverSessionsList(@Path("id") id: kotlin.String): Response<ListUserApproverSessionsResponse>

}
