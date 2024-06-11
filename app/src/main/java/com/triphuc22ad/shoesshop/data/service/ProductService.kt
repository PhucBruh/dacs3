package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.domain.model.ProductDetail
import com.triphuc22ad.shoesshop.domain.model.ProductPrice
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("/api/products")
    suspend fun getAllProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Product>>

    @GET("/api/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<DataResponse<ProductDetail>>

    @GET("/api/products/{id}/price")
    suspend fun getProductPriceById(@Path("id") id: Int): Response<DataResponse<ProductPrice>>
}