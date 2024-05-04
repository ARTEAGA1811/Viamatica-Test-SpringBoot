package com.viamatica.viamatica.rest.advice;

import com.viamatica.viamatica.domain.dto.response.ErrorResponse;
import com.viamatica.viamatica.errors.EntityNotFoundException;
import com.viamatica.viamatica.utils.ErrorCatalog;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ErrorResponse.builder()
                .code(ex.getErrorCatalog().getCode())
                .message(ex.getErrorCatalog().getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class, TransactionSystemException.class})
    public ErrorResponse handleContraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(ErrorCatalog.VALIDATION_ERROR.getCode())
                .message(ErrorCatalog.VALIDATION_ERROR.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return ErrorResponse.builder()
                .code(ErrorCatalog.ACCESS_DENIED.getCode())
                .message(ErrorCatalog.ACCESS_DENIED.getMessage())
                .details(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception) {
        log.error(exception.getMessage());
        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
