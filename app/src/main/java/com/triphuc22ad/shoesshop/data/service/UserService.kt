package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.ApiResponse
import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.domain.model.OrderInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    @GET("/api/user/me")
    suspend fun getMyInfo(): DataResponse<UserInfoResponse>

    @GET("/api/user/me/order")
    suspend fun getMyOrder(@Query(value = "completed") completed: Boolean = true): Response<DataResponse<List<OrderInfo>>>

    @POST("/api/user/me/order")
    suspend fun createOrder(@Body orderRequest: OrderRequest): Response<ApiResponse>
}

class OrderRequest(
    val description: String,
    val detail: List<OrderDetailRequest>,
    val shippingAddress: String,
)

data class OrderDetailRequest(
    val colorId: Int,
    val productId: Int,
    val quantity: Int,
    val sizeId: Int,
)
