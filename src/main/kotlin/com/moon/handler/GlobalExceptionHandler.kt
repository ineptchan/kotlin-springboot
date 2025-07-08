package com.moon.handler

import com.moon.base.Result
import com.moon.base.ValidationError
import com.moon.constant.CommonConstant
import org.slf4j.LoggerFactory
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler
    fun exceptionHandler(exception: Exception): Result<String> {
        logger.error(exception.message, exception)
        return Result.error(exception.message ?: CommonConstant.UNKNOWN_ERROR)
    }

    /**
     * Validated的验证错误
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun exceptionHandler(ex: MethodArgumentNotValidException): Result<List<ValidationError>> {
        val errors = ex.bindingResult.fieldErrors.map { fe ->
            ValidationError(
                field = fe.field,
                message = fe.defaultMessage ?: CommonConstant.ILLEGAL_PARAMETERS,
            )
        }

        return Result.error(CommonConstant.ILLEGAL_PARAMETERS, errors)
    }
}