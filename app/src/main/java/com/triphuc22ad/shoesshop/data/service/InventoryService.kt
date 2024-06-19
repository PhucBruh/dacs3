package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.InventoryRequest
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.domain.model.Inventory
import com.triphuc22ad.shoesshop.domain.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface InventoryService {

    @GET("/api/inventory/product")
    suspend fun getProductByInventoryInfo(
        @Query("productId") productId: Int = 0,
        @Query("colorId") colorId: Int = 0,
        @Query("sizeId") sizeId: Int = 0,
    ): Response<DataResponse<Product>>


    @GET("/api/inventory")
    suspend fun getAllInventory(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Inventory>>

    @GET("/api/inventory/q")
    suspend fun getAllInventory(
        @Query("query") query: String = "",
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Inventory>>

    @GET("/api/inventory/{id}")
    suspend fun getInventoryInfo(
        @Path("id") id: Int = 0,
    ): Response<DataResponse<Inventory>>

    @GET("/api/inventory/check/{id}")
    suspend fun check(@Path("id") id: Int): Response<ApiResponse>

    @PUT("/api/inventory/{id}")
    suspend fun updateStock(
        @Path("id") id: Int = 0,
        @Query("stock") stock: Int = 0,
    ): Response<ApiResponse>


    @DELETE("/api/inventory/{id}")
    suspend fun delete(
        @Path("id") id: Int,
    ): Response<ApiResponse>

    @POST("/api/inventory")
    suspend fun createInventory(@Body inventoryRequest: InventoryRequest): Response<ApiResponse>

    @PUT("/api/inventory/{id}")
    suspend fun createInventory(
        @Path("id") id: Int,
        @Query("stock") stock: Int
    ): Response<ApiResponse>
}