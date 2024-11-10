package com.kishan.pmapp.core.data.dto

data class BasicApiResponse<T> (
    val statusCode : Int,
    val data : T? = null,
    val message : String? = null,
    val success : Boolean
)