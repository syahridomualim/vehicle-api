package com.example.vehicleapp.exception

import com.example.vehicleapp.model.HttpResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import java.util.stream.Collectors
import kotlin.NoSuchElementException
import kotlin.collections.HashMap

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

        return createResponse(errorMessages, "Ups... there are errors", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun notFoundException(exception: NoSuchElementException): ResponseEntity<Any> {
        val errorMessages = HashMap<String, String?>()
        errorMessages["error"] = exception.message
        return createResponse(errorMessages, "data not found", HttpStatus.NOT_FOUND)
    }

    private fun createResponse(data: Any, message: String, httpStatus: HttpStatus): ResponseEntity<Any> {
        val httpResponse = HttpResponse(
            timestamp = Date().time,
            httpStatus = httpStatus,
            httpCode = httpStatus.value(),
            message = message,
            data = data
        )

        return ResponseEntity(httpResponse, httpStatus)
    }
}