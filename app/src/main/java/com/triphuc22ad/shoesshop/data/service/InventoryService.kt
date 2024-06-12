package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.InventoryRequest
import com.triphuc22ad.shoesshop.domain.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface InventoryService {

    @GET("/api/inventory/product")
    suspend fun getProductByInventoryInfo(
        @Query("productId") productId: Int = 0,
        @Query("colorId") colorId: Int = 0,
        @Query("sizeId") sizeId: Int = 0,
    ): Response<DataResponse<Product>>
}