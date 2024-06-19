package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.domain.model.Brand
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @GET("/api/brand/q")
    suspend fun getAllBrandByQuery(
        @Query("query") query: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Brand>>

    @GET("/api/brand/{id}")
    suspend fun getBrandById(@Path("id") id: Int): Response<DataResponse<Brand>>

    @GET("/api/brand/check/{id}")
    suspend fun check(@Path("id") id: Int): Response<ApiResponse>

    @POST("/api/brand")
    suspend fun create(@Body brand: BrandRequest): Response<DataResponse<Brand>>

    @DELETE("/api/brand/{id}")
    suspend fun delete(@Path("id") id: Int): Response<ApiResponse>

    @PUT("/api/brand/{id}")
    suspend fun update(@Path("id") id: Int, @Body brand: BrandRequest): Response<ApiResponse>

}

data class BrandRequest(
    val name: String,
    val imgUrl: String,
)