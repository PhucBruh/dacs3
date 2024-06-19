package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.data.model.ProductRequest
import com.triphuc22ad.shoesshop.domain.model.Product
import com.triphuc22ad.shoesshop.domain.model.ProductDetail
import com.triphuc22ad.shoesshop.domain.model.ProductPrice
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET("/api/products")
    suspend fun getAllProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Product>>

    @GET("/api/products/s")
    suspend fun search(
        @Query("query") query: String = "",
        @Query("isFilter") isFilter: Boolean = false,
        @Query("saleStatus") saleStatus: String = "NORMAL",
        @Query("orderBy") orderBy: String = "NAME",
        @Query("sortBy") sortBy: String = "ASCENDING",
        @Query("minPrice") minPrice: Double = 0.0,
        @Query("maxPrice") maxPrice: Double = 5000000.0,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Product>>

    @GET("/api/products/q")
    suspend fun getAllProductsByQuery(
        @Query("query") query: String = "",
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Product>>

    @GET("/api/products/brand/{id}")
    suspend fun getAllProductsByBrandId(
        @Path("id") id: Int = 0,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Product>>

    @GET("/api/products/admin")
    suspend fun getAllProductsForAdmin(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<Product>>

    @GET("/api/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<DataResponse<ProductDetail>>

    @GET("/api/products/check/{id}")
    suspend fun check(@Path("id") id: Int): Response<ApiResponse>

    @GET("/api/products/{id}/price")
    suspend fun getProductPriceById(@Path("id") id: Int): Response<DataResponse<ProductPrice>>

    @DELETE("/api/products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<ApiResponse>

    @POST("/api/products")
    suspend fun addProduct(@Body productRequest: ProductRequest): Response<ApiResponse>

    @PUT("/api/products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body productRequest: ProductRequest
    ): Response<DataResponse<ProductDetail>>

    @DELETE("/api/products/{id}/size/{sizeId}")
    suspend fun deleteSize(
        @Path("id") id: Int,
        @Path("sizeId") sizeId: Int,
    ): Response<ApiResponse>

    @DELETE("/api/products/{id}/image/{imageId}")
    suspend fun deleteImage(
        @Path("id") id: Int,
        @Path("imageId") imageId: Int,
    ): Response<ApiResponse>

    @DELETE("/api/products/{id}/colors/{colorId}")
    suspend fun deleteColor(
        @Path("id") id: Int,
        @Path("colorId") colorId: Int,
    ): Response<ApiResponse>
}