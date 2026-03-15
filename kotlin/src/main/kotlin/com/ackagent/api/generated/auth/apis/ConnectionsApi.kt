package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.CreateConnectionRequest
import com.ackagent.api.generated.auth.models.CreateConnectionResponse

interface ConnectionsApi {
    /**
     * POST api/v1/connections
     * Create connection
     * Create connection
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param createConnectionRequest Request payload
     * @return [CreateConnectionResponse]
     */
    @POST("api/v1/connections")
    suspend fun connectionsCreate(@Body createConnectionRequest: CreateConnectionRequest): Response<CreateConnectionResponse>

}
