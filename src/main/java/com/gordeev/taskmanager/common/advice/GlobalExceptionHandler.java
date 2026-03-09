package com.gordeev.taskmanager.common.advice;

import com.gordeev.taskmanager.common.dto.ApiError;
import com.gordeev.taskmanager.common.dto.ApiResponse;
import com.gordeev.taskmanager.common.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Валидация
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError fieldError) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        });

        ApiError error = new ApiError(
                "Ошибка валидации тела запроса",
                "VALIDATION_ERROR",
                errors
        );

        return ResponseEntity.badRequest().body(ApiResponse.error(error));
    }

    // Кастомные исключения
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> businessHandler(BusinessException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                ex.getErrorCode(),
                null
        );

        return ResponseEntity.status(ex.getHttpStatus()).body(ApiResponse.error(error));
    }
}
