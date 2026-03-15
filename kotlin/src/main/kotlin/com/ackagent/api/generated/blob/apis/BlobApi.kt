package com.ackagent.api.generated.blob.apis

import com.ackagent.api.generated.blob.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.blob.models.BlobRequest
import com.ackagent.api.generated.blob.models.BlobResponse

interface BlobApi {
    /**
     * POST api/v1/organizations/{orgId}/blob
     * Create encrypted blob
     * Creates a new encrypted blob for the organization. Only succeeds if no blob currently exists. Requires device attestation and org membership.
     * Responses:
     *  - 201: Encrypted blob created successfully. The ETag header contains the initial version.
     *  - 400: Invalid request payload. Verify all required fields are present and correctly encoded.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Device attestation failed or device is not authorized for write operations.
     *  - 409: A blob already exists for this organization. Use PUT to update the existing blob.
     *
     * @param orgId Organization ID
     * @param blobRequest Encrypted blob data with per-device wrapped keys and attestation.
     * @return [BlobResponse]
     */
    @POST("api/v1/organizations/{orgId}/blob")
    suspend fun blobCreate(@Path("orgId") orgId: kotlin.String, @Body blobRequest: BlobRequest): Response<BlobResponse>

    /**
     * DELETE api/v1/organizations/{orgId}/blob
     * Delete encrypted blob
     * Permanently deletes the encrypted blob for the organization. Requires device attestation and org membership. This action cannot be undone.
     * Responses:
     *  - 204: Blob deleted successfully. No content returned.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Device attestation failed or device is not authorized for delete operations.
     *
     * @param orgId Organization ID
     * @param xDeviceAuthKey Lowercase hex-encoded auth public key of the device for attestation verification.
     * @param xAssertion Base64-encoded device assertion proving control of the signing key.
     * @return [Unit]
     */
    @DELETE("api/v1/organizations/{orgId}/blob")
    suspend fun blobDelete(@Path("orgId") orgId: kotlin.String, @Header("x-device-auth-key") xDeviceAuthKey: kotlin.String, @Header("x-assertion") xAssertion: kotlin.String): Response<Unit>

    /**
     * GET api/v1/organizations/{orgId}/blob
     * Get encrypted blob
     * Retrieves the current encrypted blob for the organization. Returns the encrypted data along with per-device wrapped keys and version information. Requires organization membership.
     * Responses:
     *  - 200: Encrypted blob retrieved successfully. The ETag header contains the current version for optimistic locking.
     *  - 401: Missing or invalid authentication token. Obtain a valid token from the login service.
     *  - 404: No blob exists for this organization. Use POST to create an initial blob.
     *
     * @param orgId Organization ID
     * @return [BlobResponse]
     */
    @GET("api/v1/organizations/{orgId}/blob")
    suspend fun blobGet(@Path("orgId") orgId: kotlin.String): Response<BlobResponse>

    /**
     * PUT api/v1/organizations/{orgId}/blob
     * Update encrypted blob
     * Updates the encrypted blob using optimistic locking. The If-Match header must contain the current ETag to prevent concurrent modification conflicts.
     * Responses:
     *  - 200: Encrypted blob updated successfully. The ETag header contains the new version.
     *  - 400: Invalid request payload. Verify all required fields are present.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Device attestation failed or device is not authorized for write operations.
     *  - 404: No blob exists for this organization. Use POST to create one first.
     *  - 412: ETag mismatch - the blob was modified by another device. Fetch the latest version and retry.
     *
     * @param orgId Organization ID
     * @param ifMatch Current version ETag from the last GET or update response. Required for optimistic locking.
     * @param blobRequest Updated encrypted blob data with attestation information.
     * @return [BlobResponse]
     */
    @PUT("api/v1/organizations/{orgId}/blob")
    suspend fun blobUpdate(@Path("orgId") orgId: kotlin.String, @Header("if-match") ifMatch: kotlin.String, @Body blobRequest: BlobRequest): Response<BlobResponse>

}
