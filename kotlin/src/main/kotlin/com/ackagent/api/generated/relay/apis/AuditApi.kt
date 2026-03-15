package com.ackagent.api.generated.relay.apis

import com.ackagent.api.generated.relay.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.relay.models.AuditSyncRequest
import com.ackagent.api.generated.relay.models.AuditSyncResponse

interface AuditApi {
    /**
     * POST api/v1/audit/device/sync
     * Sync device audit chain
     * Synchronizes device audit chain entries to the server for tamper-evident logging. Each entry is cryptographically signed by the device.
     * Responses:
     *  - 200: Audit entries synchronized successfully. Returns the count of synced entries.
     *  - 400: Invalid request payload. Check entry format and signatures.
     *  - 401: Missing or invalid authentication token.
     *  - 403: Device is not authorized to sync audit entries.
     *
     * @param auditSyncRequest Audit chain entries to synchronize with device attestation.
     * @return [AuditSyncResponse]
     */
    @POST("api/v1/audit/device/sync")
    suspend fun auditDeviceSyncSync(@Body auditSyncRequest: AuditSyncRequest): Response<AuditSyncResponse>

}
