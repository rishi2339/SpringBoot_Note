package com.springBoot.note_app

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(e: MethodArgumentNotValidException) : ResponseEntity<Map<String, Any>>{
        val errors = e.bindingResult.allErrors.map {
            it.defaultMessage ?: "Invalid Value."
        }
        return ResponseEntity
            .status(400)
            .body(mapOf("errors" to errors))
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(e: BadCredentialsException): ResponseEntity<Map<String, Any>> {
        val errors = e.message ?: "Invalid Value."
        return ResponseEntity
            .status(401)
            .body(mapOf("message" to errors))
    }

}