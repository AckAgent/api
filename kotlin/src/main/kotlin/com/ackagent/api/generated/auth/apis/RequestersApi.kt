package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.CreateRequesterRequest
import com.ackagent.api.generated.auth.models.ListRequestersResponse
import com.ackagent.api.generated.auth.models.RequesterResponse

interface RequestersApi {
    /**
     * DELETE api/v1/requesters/{id}
     * Delete requester
     * Delete requester
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @return [Unit]
     */
    @DELETE("api/v1/requesters/{id}")
    suspend fun requesterByIdDelete(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * GET api/v1/requesters/{id}
     * Get requester
     * Get requester
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier
     * @return [RequesterResponse]
     */
    @GET("api/v1/requesters/{id}")
    suspend fun requesterByIdGet(@Path("id") id: kotlin.String): Response<RequesterResponse>

    /**
     * POST api/v1/requesters/{id}/revoke
     * Revoke requester
     * Revoke requester
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @return [Unit]
     */
    @POST("api/v1/requesters/{id}/revoke")
    suspend fun requesterRevokeRevoke(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * POST api/v1/requesters
     * Create requester
     * Create requester - creates a new requester registration for a user
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *  - 401: Access is unauthorized.
     *
     * @param createRequesterRequest Request payload
     * @return [RequesterResponse]
     */
    @POST("api/v1/requesters")
    suspend fun requestersCreate(@Body createRequesterRequest: CreateRequesterRequest): Response<RequesterResponse>

    /**
     * GET api/v1/users/{id}/requesters
     * List user&#39;s requesters
     * List user&#39;s requesters
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @return [ListRequestersResponse]
     */
    @GET("api/v1/users/{id}/requesters")
    suspend fun userRequestersList(@Path("id") id: kotlin.String): Response<ListRequestersResponse>

}
