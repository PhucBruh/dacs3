package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.PagedResponse
import com.triphuc22ad.shoesshop.domain.model.Brand
import com.triphuc22ad.shoesshop.domain.model.SpecialOffer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpecialOfferService {

    @GET("/api/special_offer")
    suspend fun getAllSpecialOffer(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
    ): Response<PagedResponse<SpecialOffer>>
}