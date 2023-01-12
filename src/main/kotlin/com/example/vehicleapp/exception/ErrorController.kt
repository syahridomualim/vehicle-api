package com.example.vehicleapp.exception

import com.example.vehicleapp.exception.domain.VehicleAlreadyExist
import com.example.vehicleapp.model.HttpResponse
import com.example.vehicleapp.util.createResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.temporal.ChronoField
import java.util.*
import java.util.stream.Collectors

@RestControllerAdvice
class ErrorController : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorMessages = HashMap<String, List<String?>>()
        val messages = ex.bindingResult.fieldErrors.stream().map { fieldError ->
            fieldError.defaultMessage
        }.collect(Collectors.toList())
        errorMessages["errors"] = messages
        return ResponseEntity.of(
            Optional.of(
                HttpResponse(
                    Date().time,
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value(),
                    "Ups... there are errors",
                    errorMessages
                )
            )
        )
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun notFoundException(exception: NoSuchElementException): ResponseEntity<HttpResponse<Any>> {
        val errorMessages = HashMap<String, String?>()
        errorMessages["error"] = exception.message
        return createResponse(errorMessages, "data not found", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(VehicleAlreadyExist::class)
    fun vehicleAlreadyExists(exception: VehicleAlreadyExist): ResponseEntity<HttpResponse<Any>> {
        val errorMessages = HashMap<String, String?>()
        errorMessages["error"] = exception.message
        return createResponse<Any>(errorMessages, "data already exists", HttpStatus.CONFLICT)
    }
}