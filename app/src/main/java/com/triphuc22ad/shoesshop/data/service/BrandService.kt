package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.domain.model.Brand
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BrandService {

    @GET("/api/brand")
    suspend fun getAllBrand(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Brand>>

    @GET("/api/brand/{id}")
    suspend fun getBrandById(@Path("id") id: Int): Response<DataResponse<Brand>>

    @POST("/api/brand")
    suspend fun create(@Body brand: BranRequest): Response<DataResponse<Brand>>

    @POST("/api/brand/{id}")
    suspend fun delete(@Path("id") id: Int): Response<ApiResponse>

    @PUT("/api/brand")
    suspend fun update(@Body brand: BranRequest): Response<ApiResponse>
}

data class BranRequest(
    val name: String,
    val imgUrl: String,
)