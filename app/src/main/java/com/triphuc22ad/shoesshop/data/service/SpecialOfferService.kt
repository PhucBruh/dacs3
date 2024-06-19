package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SpecialOfferService {

    @GET("/api/special_offer")
    suspend fun getAllSpecialOffer(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<SpecialOffer>>

    @GET("/api/special_offer")
    suspend fun getAllSpecialOfferByQuery(
        @Query("query") query: String = "",
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<SpecialOffer>>

    @GET("/api/special_offer/{id}")
    suspend fun getSpecialOfferById(@Path("id") id: Int): Response<DataResponse<SpecialOffer>>

    @GET("/api/special_offer/check/{id}")
    suspend fun check(@Path("id") id: Int): Response<ApiResponse>

    @GET("/api/special_offer/admin")
    suspend fun getAllSpecialOfferByAdmin(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<SpecialOffer>>

    @POST("/api/special_offer")
    suspend fun createSpecialOffer(@Body specialOfferRequest: SpecialOfferRequest): Response<ApiResponse>

    @PUT("/api/special_offer/{id}")
    suspend fun changeActive(
        @Path("id") id: Int,
        @Query("active") active: Boolean
    ): Response<ApiResponse>

    @DELETE("/api/special_offer/{id}")
    suspend fun delete(
        @Path("id") id: Int,
    ): Response<ApiResponse>
}

data class SpecialOfferRequest(
    val description: String,
    val name: String,
    val productId: Int,
    val value: Double
)