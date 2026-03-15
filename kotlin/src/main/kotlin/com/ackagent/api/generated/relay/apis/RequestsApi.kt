package com.ackagent.api.generated.relay.apis

import com.ackagent.api.generated.relay.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.relay.models.CallbackRequest
import com.ackagent.api.generated.relay.models.CallbacksSubmit200Response
import com.ackagent.api.generated.relay.models.CreateSigningRequest
import com.ackagent.api.generated.relay.models.CreateSigningResponse
import com.ackagent.api.generated.relay.models.PendingRequest
import com.ackagent.api.generated.relay.models.SigningRequestStatus

interface RequestsApi {
    /**
     * POST api/v1/callbacks/{id}
     * Submit signing response
     * Submits an encrypted signing response from an approver device. The response is encrypted end-to-end and the relay cannot read its contents.
     * Responses:
     *  - 200: Response submitted successfully.
     *  - 400: Invalid response payload. Check encryption format.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Device is not authorized to respond to this request.
     *  - 404: Request not found or already expired.
     *  - 409: Request has already been responded to by another device.
     *  - 410: Request has expired and can no longer be responded to.
     *
     * @param id Request ID to submit the response for.
     * @param callbackRequest Encrypted response from the approver device.
     * @return [CallbacksSubmit200Response]
     */
    @POST("api/v1/callbacks/{id}")
    suspend fun callbacksSubmit(@Path("id") id: kotlin.String, @Body callbackRequest: CallbackRequest): Response<CallbacksSubmit200Response>

    /**
     * GET api/v1/requests/{id}
     * Get request status
     * Polls the status of a signing request. Returns the encrypted response when available. Use exponential backoff when polling.
     * Responses:
     *  - 200: Request status retrieved successfully.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Not authorized to view this request.
     *  - 404: Request not found.
     *
     * @param id Request ID to check status for.
     * @return [SigningRequestStatus]
     */
    @GET("api/v1/requests/{id}")
    suspend fun requestByIdGet(@Path("id") id: kotlin.String): Response<SigningRequestStatus>

    /**
     * POST api/v1/requests
     * Create signing request
     * Creates a new signing request that will be routed to the user&#39;s approver devices. The request payload is encrypted end-to-end.
     * Responses:
     *  - 200: Request created and approved synchronously (rare, only for pre-approved requesters).
     *  - 202: Request created and pending approval. Poll the request status endpoint for updates.
     *  - 400: Invalid request payload. Check required fields and encryption.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Requester is not authorized to create signing requests.
     *  - 404: User or requester not found.
     *
     * @param createSigningRequest Encrypted signing request with metadata and wrapped keys.
     * @return [CreateSigningResponse]
     */
    @POST("api/v1/requests")
    suspend fun requestsCreate(@Body createSigningRequest: CreateSigningRequest): Response<CreateSigningResponse>

    /**
     * GET api/v1/users/{id}/pending
     * List pending requests
     * Lists all pending signing requests for a user. Called by approver devices to fetch requests when push notification is missed or for initial sync.
     * Responses:
     *  - 200: Pending requests retrieved successfully.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Not authorized to view pending requests for this user.
     *
     * @param id User ID to list pending requests for.
     * @return [kotlin.collections.List<PendingRequest>]
     */
    @GET("api/v1/users/{id}/pending")
    suspend fun userPendingList(@Path("id") id: kotlin.String): Response<kotlin.collections.List<PendingRequest>>

}
