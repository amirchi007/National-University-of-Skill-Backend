package com.nus.National_University_of_Skill_Backend.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntime(e: RuntimeException) =
        ResponseEntity(ErrorResponse(400, e.message ?: "Bad Request"), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound(e: EntityNotFoundException) =
        ResponseEntity(ErrorResponse(404, e.message ?: "Not found"), HttpStatus.NOT_FOUND)
}
