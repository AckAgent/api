package com.ackagent.api.generated.org.apis

import com.ackagent.api.generated.org.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.org.models.AddMemberRequest
import com.ackagent.api.generated.org.models.ListMembersResponse
import com.ackagent.api.generated.org.models.MemberInfo
import com.ackagent.api.generated.org.models.MemberUpdatedResponse
import com.ackagent.api.generated.org.models.UpdateMemberRequest

interface OrganizationMembersApi {
    /**
     * DELETE api/v1/organizations/{id}/members/{uid}
     * Remove organization member
     * Remove organization member - requires admin role
     * Responses:
     *  - 200: The request has succeeded.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier
     * @param uid User identifier
     * @return [MemberUpdatedResponse]
     */
    @DELETE("api/v1/organizations/{id}/members/{uid}")
    suspend fun organizationMemberByIdRemove(@Path("id") id: kotlin.String, @Path("uid") uid: kotlin.String): Response<MemberUpdatedResponse>

    /**
     * PUT api/v1/organizations/{id}/members/{uid}
     * Update organization member
     * Update organization member - requires admin role
     * Responses:
     *  - 200: The request has succeeded.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier
     * @param uid User identifier
     * @param updateMemberRequest Request payload
     * @return [MemberUpdatedResponse]
     */
    @PUT("api/v1/organizations/{id}/members/{uid}")
    suspend fun organizationMemberByIdUpdate(@Path("id") id: kotlin.String, @Path("uid") uid: kotlin.String, @Body updateMemberRequest: UpdateMemberRequest): Response<MemberUpdatedResponse>

    /**
     * POST api/v1/organizations/{id}/members
     * Add organization member
     * Add organization member - requires admin role
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 404: The server cannot find the requested resource.
     *  - 409: The request conflicts with the current state of the server.
     *
     * @param id Unique identifier
     * @param addMemberRequest Request payload
     * @return [MemberInfo]
     */
    @POST("api/v1/organizations/{id}/members")
    suspend fun organizationMembersAdd(@Path("id") id: kotlin.String, @Body addMemberRequest: AddMemberRequest): Response<MemberInfo>

    /**
     * GET api/v1/organizations/{id}/members
     * List organization members
     * List organization members
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Unique identifier
     * @return [ListMembersResponse]
     */
    @GET("api/v1/organizations/{id}/members")
    suspend fun organizationMembersList(@Path("id") id: kotlin.String): Response<ListMembersResponse>

}
