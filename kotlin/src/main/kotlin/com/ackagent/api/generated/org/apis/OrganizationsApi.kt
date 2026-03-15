package com.ackagent.api.generated.org.apis

import com.ackagent.api.generated.org.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.ackagent.api.generated.org.models.CreateOrganizationRequest
import com.ackagent.api.generated.org.models.Organization
import com.ackagent.api.generated.org.models.OrganizationFullInfo
import com.ackagent.api.generated.org.models.UpdateOrganizationRequest
import com.ackagent.api.generated.org.models.VerifyEmailResponse

interface OrganizationsApi {
    /**
     * GET api/v1/organizations/{id}
     * Get organization by ID
     * Get organization by ID - requires org membership
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *  - 404: The server cannot find the requested resource.
     *
     * @param id Unique identifier
     * @return [OrganizationFullInfo]
     */
    @GET("api/v1/organizations/{id}")
    suspend fun organizationByIdGet(@Path("id") id: kotlin.String): Response<OrganizationFullInfo>

    /**
     * PUT api/v1/organizations/{id}
     * Update organization
     * Update organization - requires admin role
     * Responses:
     *  - 200: The request has succeeded.
     *  - 401: Access is unauthorized.
     *  - 403: Access is forbidden.
     *
     * @param id Unique identifier
     * @param updateOrganizationRequest Request payload
     * @return [OrganizationFullInfo]
     */
    @PUT("api/v1/organizations/{id}")
    suspend fun organizationByIdUpdate(@Path("id") id: kotlin.String, @Body updateOrganizationRequest: UpdateOrganizationRequest): Response<OrganizationFullInfo>

    /**
     * GET api/v1/org-by-slug/{slug}
     * Get organization by slug
     * Get organization by slug - public endpoint for enrollment
     * Responses:
     *  - 200: The request has succeeded.
     *  - 404: The server cannot find the requested resource.
     *
     * @param slug URL-friendly organization identifier
     * @return [Organization]
     */
    @GET("api/v1/org-by-slug/{slug}")
    suspend fun organizationBySlugGet(@Path("slug") slug: kotlin.String): Response<Organization>

    /**
     * POST api/v1/organizations/verify-email
     * Verify organization email
     * Verify organization email - verifies admin email with token
     * Responses:
     *  - 200: The request has succeeded.
     *  - 400: The server could not understand the request due to invalid syntax.
     *
     * @param token Verification token
     * @return [VerifyEmailResponse]
     */
    @POST("api/v1/organizations/verify-email")
    suspend fun organizationVerifyEmailVerify(@Query("token") token: kotlin.String): Response<VerifyEmailResponse>

    /**
     * POST api/v1/organizations
     * Create organization
     * Create organization - self-service signup with email verification
     * Responses:
     *  - 201: The request has succeeded and a new resource has been created as a result.
     *  - 400: The server could not understand the request due to invalid syntax.
     *  - 409: The request conflicts with the current state of the server.
     *
     * @param createOrganizationRequest Request payload
     * @return [OrganizationFullInfo]
     */
    @POST("api/v1/organizations")
    suspend fun organizationsCreate(@Body createOrganizationRequest: CreateOrganizationRequest): Response<OrganizationFullInfo>

}
