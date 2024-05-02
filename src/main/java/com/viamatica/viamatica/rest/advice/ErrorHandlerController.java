package com.viamatica.viamatica.rest.advice;

import com.viamatica.viamatica.domain.dto.response.ErrorResponse;
import com.viamatica.viamatica.errors.EntityNotFoundException;
import com.viamatica.viamatica.utils.ErrorCatalog;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ErrorHandlerController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        return ErrorResponse.builder()
                .code(ex.getErrorCatalog().getCode())
                .message(ex.getErrorCatalog().getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleContraintViolationException(ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();

        ex.getConstraintViolations().forEach(
                r -> {
                    details.add(r.getMessage());
                }
        );
        return ErrorResponse.builder()
                .code(ErrorCatalog.VALIDATION_ERROR.getCode())
                .message(ErrorCatalog.VALIDATION_ERROR.getMessage())
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();
    }

//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(AccessDeniedException.class)
//    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
//        return ErrorResponse.builder()
//                .code(ErrorCatalog.ACCESS_DENIED.getCode())
//                .message(ErrorCatalog.ACCESS_DENIED.getMessage())
//                .details(Collections.singletonList(ex.getMessage()))
//                .timestamp(LocalDateTime.now())
//                .build();
//    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
