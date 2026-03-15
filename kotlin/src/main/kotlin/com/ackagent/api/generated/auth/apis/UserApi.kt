package com.ackagent.api.generated.auth.apis

import com.ackagent.api.generated.auth.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.auth.models.ListLoginHistoryResponse

interface UserApi {
    /**
     * GET api/v1/users/{id}/login-history
     * List login history
     * List recent login attempts for the user, including pending and completed sessions
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *
     * @param id Unique identifier
     * @param limit Maximum number of results to return (optional, default to 50)
     * @return [ListLoginHistoryResponse]
     */
    @GET("api/v1/users/{id}/login-history")
    suspend fun userLoginHistoryList(@Path("id") id: kotlin.String, @Query("limit") limit: kotlin.Int? = 50): Response<ListLoginHistoryResponse>

}
