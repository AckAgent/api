package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.HealthResponse

interface HealthApi {
    /**
     * GET health
     * Health check
     * Health check
     * Responses:
     *  - 200: The request has succeeded.
     *
     * @return [HealthResponse]
     */
    @GET("health")
    suspend fun healthCheck(): Response<HealthResponse>

    /**
     * GET health/ready
     * Readiness check
     * Readiness check - includes database connectivity check
     * Responses:
     *  - 200: The request has succeeded.
     *  - 503: Service unavailable.
     *
     * @return [HealthResponse]
     */
    @GET("health/ready")
    suspend fun healthReadyCheck(): Response<HealthResponse>

}
