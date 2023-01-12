package com.example.vehicleapp.util

import com.example.vehicleapp.model.HttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

fun <T> createResponse(data: T, message: String, httpStatus: HttpStatus): ResponseEntity<HttpResponse<T>> {
    val httpResponse = HttpResponse(
        timestamp = Date().time,
        httpStatus = httpStatus,
        httpCode = httpStatus.value(),
        message = message,
        data = data
    )

    return ResponseEntity(httpResponse, httpStatus)
}