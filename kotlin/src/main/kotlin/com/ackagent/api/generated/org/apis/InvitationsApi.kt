package com.ackagent.api.generated.org.apis

import com.ackagent.api.generated.org.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.org.models.AcceptInvitationRequest
import com.ackagent.api.generated.org.models.AcceptInvitationResponse
import com.ackagent.api.generated.org.models.CreateInvitationRequest
import com.ackagent.api.generated.org.models.InvitationInfo
import com.ackagent.api.generated.org.models.InvitationRevokedResponse
import com.ackagent.api.generated.org.models.ListInvitationsResponse

interface InvitationsApi {
    /**
     * POST api/v1/invitations/accept
     * Accept invitation
     * Accept an organization invitation using a token from the invitation email
     * Responses:
     *  - 200: The request has succeeded.
     *  - 400: Invalid or expired invitation token.
     *  - 409: User is already a member of the organization.
     *
     * @param acceptInvitationRequest Request payload
     * @return [AcceptInvitationResponse]
     */
    @POST("api/v1/invitations/accept")
    suspend fun invitationAcceptAccept(@Body acceptInvitationRequest: AcceptInvitationRequest): Response<AcceptInvitationResponse>

    /**
     * DELETE api/v1/organizations/{id}/invitations/{invid}
     * Revoke invitation
     * Revoke invitation - requires admin role
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier
     * @param invid Invitation identifier
     * @return [InvitationRevokedResponse]
     */
    @DELETE("api/v1/organizations/{id}/invitations/{invid}")
    suspend fun organizationInvitationByIdRevoke(@Path("id") id: kotlin.String, @Path("invid") invid: kotlin.String): Response<InvitationRevokedResponse>

    /**
     * POST api/v1/organizations/{id}/invitations
     * Create invitation
     * Create invitation - requires admin role
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 409: The request conflicts with the current state of the server.
     *
     * @param id Unique identifier
     * @param createInvitationRequest Request payload
     * @return [InvitationInfo]
     */
    @POST("api/v1/organizations/{id}/invitations")
    suspend fun organizationInvitationsCreate(@Path("id") id: kotlin.String, @Body createInvitationRequest: CreateInvitationRequest): Response<InvitationInfo>

    /**
     * GET api/v1/organizations/{id}/invitations
     * List invitations
     * List invitations - requires admin role
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Unique identifier
     * @return [ListInvitationsResponse]
     */
    @GET("api/v1/organizations/{id}/invitations")
    suspend fun organizationInvitationsList(@Path("id") id: kotlin.String): Response<ListInvitationsResponse>

}
