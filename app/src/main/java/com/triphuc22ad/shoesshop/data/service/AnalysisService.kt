package com.triphuc22ad.shoesshop.data.service

import com.triphuc22ad.shoesshop.data.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnalysisService {

    @GET("/api/analysis")
    suspend fun getAnalysis(): Response<DataResponse<Analysis>>

    @GET("/api/analysis/monthly")
    suspend fun getMonthlyReport(
        @Query("month") month: Int = 1,
        @Query("year") year: Int = 1,
    ): Response<List<WeeklyReport>>

}

data class Analysis(
    val completedOrder: Int = 0,
    val incompleteOrder: Int = 0,
    val monthly: Double = 0.0,
    val yearly: Double = 0.0
)

data class WeeklyReport(
    val completedOrder: Int,
    val earn: Double,
    val weekEnd: String,
    val weekStart: String
)
