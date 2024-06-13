package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.domain.model.OrderDetail
import com.triphuc22ad.shoesshop.domain.model.OrderInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderService {

    @GET("/api/order")
    suspend fun getAllOrders(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<OrderInfo>>

    @GET("/api/order/{id}")
    suspend fun getOrderById(@Path("id") id: Int): Response<DataResponse<OrderDetail>>

    @GET("/api/order/{id}")
    suspend fun updateStatus(
        @Path("id") id: Int,
        @Query("status") status: String,
    ): Response<ApiResponse>
}