package com.ackagent.api.generated.blob.apis

import com.ackagent.api.generated.blob.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.blob.models.BlobResponse
import com.ackagent.api.generated.blob.models.HistoryDetailResponse
import com.ackagent.api.generated.blob.models.HistoryListResponse

interface HistoryApi {
    /**
     * GET api/v1/organizations/{orgId}/blob/history
     * List blob history
     * Returns a list of previous blob versions for the organization. Each entry contains the version number and creation timestamp. Use the version number to retrieve or restore a specific version.
     * Responses:
     *  - 200: History list retrieved successfully.
     *  - 401: Missing or invalid authentication token.
     *
     * @param orgId Organization ID
     * @param limit Maximum number of versions to return. Defaults to 20, maximum is 100. (optional, default to 20)
     * @return [HistoryListResponse]
     */
    @GET("api/v1/organizations/{orgId}/blob/history")
    suspend fun blobHistoryList(@Path("orgId") orgId: kotlin.String, @Query("limit") limit: kotlin.Int? = 20): Response<HistoryListResponse>

    /**
     * GET api/v1/organizations/{orgId}/blob/history/{version}
     * Get history version
     * Retrieves a specific historical version of the encrypted blob. Can be used to review previous data before restoring.
     * Responses:
     *  - 200: Historical version retrieved successfully.
     *  - 401: Missing or invalid authentication token.
     *  - 404: Specified version does not exist in the history.
     *
     * @param orgId Organization ID
     * @param version Version number to retrieve.
     * @return [HistoryDetailResponse]
     */
    @GET("api/v1/organizations/{orgId}/blob/history/{version}")
    suspend fun blobHistoryVersionGet(@Path("orgId") orgId: kotlin.String, @Path("version") version: kotlin.Int): Response<HistoryDetailResponse>

    /**
     * POST api/v1/organizations/{orgId}/blob/restore/{version}
     * Restore blob from history
     * Restores the blob to a previous version from history. Creates a new version with the historical data. Requires device attestation and org membership.
     * Responses:
     *  - 200: Blob restored successfully to the current version.
     *  - 201: Blob created from historical version (when no current blob exists).
     *  - 401: Missing or invalid authentication token.
     *  - 403: Device attestation failed or device is not authorized for restore operations.
     *  - 404: Specified version does not exist in the history.
     *
     * @param orgId Organization ID
     * @param version Version number to restore from.
     * @param xDeviceAuthKey Lowercase hex-encoded auth public key of the device for attestation verification.
     * @param xAssertion Base64-encoded device assertion proving control of the signing key.
     * @return [BlobResponse]
     */
    @POST("api/v1/organizations/{orgId}/blob/restore/{version}")
    suspend fun blobRestoreRestore(@Path("orgId") orgId: kotlin.String, @Path("version") version: kotlin.Int, @Header("x-device-auth-key") xDeviceAuthKey: kotlin.String, @Header("x-assertion") xAssertion: kotlin.String): Response<BlobResponse>

}
