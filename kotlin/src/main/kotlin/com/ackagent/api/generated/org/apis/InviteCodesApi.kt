package com.ackagent.api.generated.org.apis

import com.ackagent.api.generated.org.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.org.models.CreateInviteCodeRequest
import com.ackagent.api.generated.org.models.InviteCodeInfo
import com.ackagent.api.generated.org.models.InviteCodePreviewResponse
import com.ackagent.api.generated.org.models.InviteCodeRevokedResponse
import com.ackagent.api.generated.org.models.JoinOrganizationRequest
import com.ackagent.api.generated.org.models.JoinOrganizationResponse
import com.ackagent.api.generated.org.models.ListInviteCodesResponse

interface InviteCodesApi {
    /**
     * GET api/v1/invite-codes/{code}
     * Preview invite code
     * Get organization preview from an invite code. Public endpoint, no auth required.
     * Responses:
     *  - 200: The request has succeeded.
     *  - 404: Invalid or expired invite code.
     *
     * @param code Invite code (XXX-YYY-ZZZ format)
     * @return [InviteCodePreviewResponse]
     */
    @GET("api/v1/invite-codes/{code}")
    suspend fun inviteCodePreviewGet(@Path("code") code: kotlin.String): Response<InviteCodePreviewResponse>

    /**
     * DELETE api/v1/organizations/{id}/invite-codes/{codeId}
     * Revoke invite code
     * Revoke an invite code. Requires owner role.
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Organization ID
     * @param codeId Invite code ID
     * @return [InviteCodeRevokedResponse]
     */
    @DELETE("api/v1/organizations/{id}/invite-codes/{codeId}")
    suspend fun organizationInviteCodeByIdRevoke(@Path("id") id: kotlin.String, @Path("codeId") codeId: kotlin.String): Response<InviteCodeRevokedResponse>

    /**
     * POST api/v1/organizations/{id}/invite-codes
     * Create invite code
     * Create an invite code for the organization. Requires owner role.
     * Responses:
     *  - 201: Invite code created successfully.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Organization ID
     * @param createInviteCodeRequest Request payload
     * @return [InviteCodeInfo]
     */
    @POST("api/v1/organizations/{id}/invite-codes")
    suspend fun organizationInviteCodesCreate(@Path("id") id: kotlin.String, @Body createInviteCodeRequest: CreateInviteCodeRequest): Response<InviteCodeInfo>

    /**
     * GET api/v1/organizations/{id}/invite-codes
     * List invite codes
     * List all invite codes for the organization. Requires owner role.
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Organization ID
     * @return [ListInviteCodesResponse]
     */
    @GET("api/v1/organizations/{id}/invite-codes")
    suspend fun organizationInviteCodesList(@Path("id") id: kotlin.String): Response<ListInviteCodesResponse>

    /**
     * POST api/v1/organizations/join
     * Join organization via invite code
     * Join an organization using an invite code. Requires authentication.
     * Responses:
     *  - 200: Successfully joined the organization.
     *  - 400: Invalid invite code or organization at capacity.
     *  - 401: Access is unauthorized.
     *  - 409: Already a member of this organization.
     *
     * @param joinOrganizationRequest Request payload
     * @return [JoinOrganizationResponse]
     */
    @POST("api/v1/organizations/join")
    suspend fun organizationJoinJoin(@Body joinOrganizationRequest: JoinOrganizationRequest): Response<JoinOrganizationResponse>

}
