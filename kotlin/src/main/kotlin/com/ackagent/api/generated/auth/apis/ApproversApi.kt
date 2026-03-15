package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.ApproverKeysResponse
import com.ackagent.api.generated.auth.models.ApproverLinkRequest
import com.ackagent.api.generated.auth.models.ApproverLinkResponse
import com.ackagent.api.generated.auth.models.ListApproversResponse
import com.ackagent.api.generated.auth.models.UpdateApproverRequest

interface ApproversApi {
    /**
     * POST api/v1/approvers/link
     * Link approver device to account
     * Link approver device to account - links an iOS/Android device to the authenticated user&#39;s account
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 503: Credential service unavailable. The credential-issuer service is down or has no active signing key. Retry after a short delay.
     *
     * @param approverLinkRequest Request payload
     * @return [ApproverLinkResponse]
     */
    @POST("api/v1/approvers/link")
    suspend fun approversLinkLink(@Body approverLinkRequest: ApproverLinkRequest): Response<ApproverLinkResponse>

    /**
     * DELETE api/v1/users/{id}/approvers/{approverId}
     * Remove approver device
     * Remove approver device
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Unique identifier
     * @param approverId Approver device UUID
     * @return [Unit]
     */
    @DELETE("api/v1/users/{id}/approvers/{approverId}")
    suspend fun userApproverByIdRemove(@Path("id") id: kotlin.String, @Path("approverId") approverId: kotlin.String): Response<Unit>

    /**
     * PUT api/v1/users/{id}/approvers/{approverId}
     * Update approver device
     * Update approver device - update device info (APNs token, device type, etc.)
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Unique identifier
     * @param approverId Approver device UUID
     * @param updateApproverRequest Request payload
     * @return [Unit]
     */
    @PUT("api/v1/users/{id}/approvers/{approverId}")
    suspend fun userApproverByIdUpdate(@Path("id") id: kotlin.String, @Path("approverId") approverId: kotlin.String, @Body updateApproverRequest: UpdateApproverRequest): Response<Unit>

    /**
     * GET api/v1/users/{id}/approver-keys
     * Get approver signing keys
     * Get approver signing keys - returns P-256 public keys for all user&#39;s approver devices (for SAS computation)
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @return [ApproverKeysResponse]
     */
    @GET("api/v1/users/{id}/approver-keys")
    suspend fun userApproverKeysGet(@Path("id") id: kotlin.String): Response<ApproverKeysResponse>

    /**
     * GET api/v1/users/{id}/approvers
     * List user&#39;s approver devices
     * List user&#39;s approver devices
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Unique identifier
     * @return [ListApproversResponse]
     */
    @GET("api/v1/users/{id}/approvers")
    suspend fun userApproversList(@Path("id") id: kotlin.String): Response<ListApproversResponse>

}
