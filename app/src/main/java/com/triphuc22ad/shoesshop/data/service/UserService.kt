package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.DataResponse
import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.domain.model.OrderInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/api/user/me")
    suspend fun getMyInfo(): DataResponse<UserInfoResponse>

    @GET("/api/user/me/order")
    suspend fun getMyOrder(@Query(value = "completed") completed: Boolean = true): Response<DataResponse<List<OrderInfo>>>
}