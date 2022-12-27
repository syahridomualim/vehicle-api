package com.example.vehicleapp.model

import org.springframework.http.HttpStatus

data class HttpResponse <T>(
    val timestamp: Long,
    val httpStatus: HttpStatus,
    val httpCode: Int,
    val message: String,
    val data: T
)