package com.ackagent.api.generated.org.apis

import com.ackagent.api.generated.org.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.org.models.CreateServiceAccountRequest
import com.ackagent.api.generated.org.models.CreateServiceAccountResponse
import com.ackagent.api.generated.org.models.ListServiceAccountsResponse
import com.ackagent.api.generated.org.models.ServiceAccountDeletedResponse

interface ServiceAccountsApi {
    /**
     * DELETE api/v1/organizations/{id}/service-accounts/{said}
     * Delete service account
     * Delete service account - requires admin role
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier
     * @param said Service account identifier
     * @return [ServiceAccountDeletedResponse]
     */
    @DELETE("api/v1/organizations/{id}/service-accounts/{said}")
    suspend fun organizationServiceAccountByIdDelete(@Path("id") id: kotlin.String, @Path("said") said: kotlin.String): Response<ServiceAccountDeletedResponse>

    /**
     * POST api/v1/organizations/{id}/service-accounts
     * Create service account
     * Create service account - requires admin role, returns API key only on creation
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 409: The request conflicts with the current state of the server.
     *
     * @param id Unique identifier
     * @param createServiceAccountRequest Request payload
     * @return [CreateServiceAccountResponse]
     */
    @POST("api/v1/organizations/{id}/service-accounts")
    suspend fun organizationServiceAccountsCreate(@Path("id") id: kotlin.String, @Body createServiceAccountRequest: CreateServiceAccountRequest): Response<CreateServiceAccountResponse>

    /**
     * GET api/v1/organizations/{id}/service-accounts
     * List service accounts
     * List service accounts - requires admin role
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Unique identifier
     * @return [ListServiceAccountsResponse]
     */
    @GET("api/v1/organizations/{id}/service-accounts")
    suspend fun organizationServiceAccountsList(@Path("id") id: kotlin.String): Response<ListServiceAccountsResponse>

}
