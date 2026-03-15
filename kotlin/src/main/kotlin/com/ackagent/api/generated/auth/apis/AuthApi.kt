package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.ChallengeResponse
import com.ackagent.api.generated.auth.models.CorsOriginsResponse
import com.ackagent.api.generated.auth.models.ErrorResponse
import com.ackagent.api.generated.auth.models.GetKeysResponse
import com.ackagent.api.generated.auth.models.GetMeResponse
import com.ackagent.api.generated.auth.models.ReLoginRequest
import com.ackagent.api.generated.auth.models.ReLoginResponse
import com.ackagent.api.generated.auth.models.RegisterRequest
import com.ackagent.api.generated.auth.models.RegisterResponse

interface AuthApi {
    /**
     * GET api/auth/challenge
     * Get authentication challenge
     * Get authentication challenge - returns a challenge for signing key authentication
     * Responses:
     *  - 200: The request has succeeded.
     *
     * @return [ChallengeResponse]
     */
    @GET("api/auth/challenge")
    suspend fun authChallengeGet(): Response<ChallengeResponse>

    /**
     * GET api/auth/cors/origins
     * Get allowed CORS origins
     * Get allowed CORS origins
     * Responses:
     *  - 200: The request has succeeded.
     *
     * @return [CorsOriginsResponse]
     */
    @GET("api/auth/cors/origins")
    suspend fun authCorsOriginsGet(): Response<CorsOriginsResponse>

    /**
     * DELETE api/auth/keys/{id}
     * Delete a signing key
     * Delete a signing key
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier for the signing key
     * @return [Unit]
     */
    @DELETE("api/auth/keys/{id}")
    suspend fun authKeyByIdDelete(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * GET api/auth/keys
     * Get user&#39;s signing keys
     * Get user&#39;s signing keys
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @return [GetKeysResponse]
     */
    @GET("api/auth/keys")
    suspend fun authKeysList(): Response<GetKeysResponse>

    /**
     * GET api/auth/me
     * Get current user info
     * Get current user info
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @return [GetMeResponse]
     */
    @GET("api/auth/me")
    suspend fun authMeGet(): Response<GetMeResponse>

    /**
     * POST api/auth/relogin
     * Re-login with existing auth key
     * Re-login with existing auth key - authenticate using a previously registered P-256 auth key
     * Responses:
     *  - 200: The request has succeeded.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 401: Access is unauthorized.
     *
     * @param reLoginRequest Request payload
     * @return [ReLoginResponse]
     */
    @POST("api/auth/relogin")
    suspend fun authReLoginRelogin(@Body reLoginRequest: ReLoginRequest): Response<ReLoginResponse>

    /**
     * POST api/auth/register
     * Register new user with auth key
     * Register new user with auth key
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 409: The request conflicts with the current state of the server.
     *
     * @param registerRequest Request payload
     * @return [RegisterResponse]
     */
    @POST("api/auth/register")
    suspend fun authRegisterRegister(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

}
