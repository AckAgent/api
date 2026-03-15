package com.ackagent.api.generated.relay.apis

import com.ackagent.api.generated.relay.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.relay.models.CallbacksSubmit200Response

interface HealthApi {
    /**
     * GET api/v1/health
     * Health check
     * Returns the current health status of the relay service. Used by load balancers and monitoring systems.
     * Responses:
     *  - 200: Service is healthy and accepting requests.
     *
     * @return [CallbacksSubmit200Response]
     */
    @GET("api/v1/health")
    suspend fun healthCheck(): Response<CallbacksSubmit200Response>

    /**
     * GET api/v1/health/ready
     * Readiness check
     * Returns the readiness status including database and OIDC connectivity. Used by orchestrators to determine if the service can accept traffic.
     * Responses:
     *  - 200: Service is ready and all dependencies are healthy.
     *  - 503: Service is not ready. Database or OIDC provider may be unavailable.
     *
     * @return [CallbacksSubmit200Response]
     */
    @GET("api/v1/health/ready")
    suspend fun healthReadyCheck(): Response<CallbacksSubmit200Response>

}
