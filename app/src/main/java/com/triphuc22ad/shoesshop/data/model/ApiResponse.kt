package com.triphuc22ad.shoesshop.data.model

data class ApiResponse(
    val success: Boolean,
    val message: String?,
)

data class DataResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: T?,
)

data class PagedResponse<T>(
    val content: List<T>,
    val last: Boolean,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
)
