package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.ClaimRequesterSessionRequest
import com.ackagent.api.generated.auth.models.ClaimRequesterSessionResponse
import com.ackagent.api.generated.auth.models.CreateRequesterSessionRequest
import com.ackagent.api.generated.auth.models.CreateRequesterSessionResponse
import com.ackagent.api.generated.auth.models.GetRequesterSessionStatusResponse
import com.ackagent.api.generated.auth.models.GetRequesterSessionTokensResponse
import com.ackagent.api.generated.auth.models.ListUserRequesterSessionsResponse
import com.ackagent.api.generated.auth.models.VerifyRequesterSessionRequest
import com.ackagent.api.generated.auth.models.VerifyRequesterSessionResponse

interface RequesterSessionsApi {
    /**
     * POST api/v1/requester-sessions/{id}/claim
     * Claim requester session
     * Claim requester session - iOS device claims a requester session after scanning the QR code
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 404: The server cannot find the requested resource.
     *  - 410: Client error
     *
     * @param id Unique identifier
     * @param claimRequesterSessionRequest Request payload
     * @return [ClaimRequesterSessionResponse]
     */
    @POST("api/v1/requester-sessions/{id}/claim")
    suspend fun requesterSessionClaimClaim(@Path("id") id: kotlin.String, @Body claimRequesterSessionRequest: ClaimRequesterSessionRequest): Response<ClaimRequesterSessionResponse>

    /**
     * POST api/v1/requester-sessions/{id}/reject
     * Reject SAS
     * Reject SAS - iOS device rejects the SAS (mismatch or user declined)
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @return [Unit]
     */
    @POST("api/v1/requester-sessions/{id}/reject")
    suspend fun requesterSessionRejectReject(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * GET api/v1/requester-sessions/{id}/status
     * Poll requester session status
     * Poll requester session status
     * Responses:
     *  - 200: The request has succeeded.
     *  - 404: The server cannot find the requested resource.
     *  - 410: Client error
     *
     * @param id Unique identifier
     * @return [GetRequesterSessionStatusResponse]
     */
    @GET("api/v1/requester-sessions/{id}/status")
    suspend fun requesterSessionStatusGet(@Path("id") id: kotlin.String): Response<GetRequesterSessionStatusResponse>

    /**
     * GET api/v1/requester-sessions/{id}/tokens
     * Get tokens after verification
     * Get tokens after verification - CLI fetches OAuth tokens after SAS verification is complete
     * Responses:
     *  - 200: The request has succeeded.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier
     * @return [GetRequesterSessionTokensResponse]
     */
    @GET("api/v1/requester-sessions/{id}/tokens")
    suspend fun requesterSessionTokensGet(@Path("id") id: kotlin.String): Response<GetRequesterSessionTokensResponse>

    /**
     * POST api/v1/requester-sessions/{id}/verify
     * Verify SAS match
     * Verify SAS match - iOS device confirms the SAS displayed on CLI matches
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @param verifyRequesterSessionRequest Request payload
     * @return [VerifyRequesterSessionResponse]
     */
    @POST("api/v1/requester-sessions/{id}/verify")
    suspend fun requesterSessionVerifyVerify(@Path("id") id: kotlin.String, @Body verifyRequesterSessionRequest: VerifyRequesterSessionRequest): Response<VerifyRequesterSessionResponse>

    /**
     * POST api/v1/requester-sessions
     * Create requester session
     * Create requester session
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *
     * @param createRequesterSessionRequest Request payload
     * @return [CreateRequesterSessionResponse]
     */
    @POST("api/v1/requester-sessions")
    suspend fun requesterSessionsCreate(@Body createRequesterSessionRequest: CreateRequesterSessionRequest): Response<CreateRequesterSessionResponse>

    /**
     * GET api/v1/users/{id}/requester-sessions
     * List pending requester sessions
     * List pending requester sessions - iOS devices poll this to see pending requester sessions for the user
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @return [ListUserRequesterSessionsResponse]
     */
    @GET("api/v1/users/{id}/requester-sessions")
    suspend fun userRequesterSessionsList(@Path("id") id: kotlin.String): Response<ListUserRequesterSessionsResponse>

}
