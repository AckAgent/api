package com.ackagent.api.generated.blob.apis

import com.ackagent.api.generated.blob.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.blob.models.HealthCheck200Response

interface HealthApi {
    /**
     * GET api/v1/health
     * Health check
     * Returns the current health status of the blob service. Used by load balancers and monitoring systems.
     * Responses:
     *  - 200: Service is healthy and accepting requests.
     *
     * @return [HealthCheck200Response]
     */
    @GET("api/v1/health")
    suspend fun healthCheck(): Response<HealthCheck200Response>

    /**
     * GET api/v1/health/ready
     * Readiness check
     * Returns the readiness status including database connectivity. Used by orchestrators to determine if the service can accept traffic.
     * Responses:
     *  - 200: Service is ready and all dependencies are healthy.
     *  - 503: Service is not ready. Database or other dependencies may be unavailable.
     *
     * @return [HealthCheck200Response]
     */
    @GET("api/v1/health/ready")
    suspend fun healthReadyCheck(): Response<HealthCheck200Response>

}
