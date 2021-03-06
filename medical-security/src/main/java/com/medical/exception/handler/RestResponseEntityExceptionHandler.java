package com.medical.exception.handler;

import com.medical.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        this.logger.error("400 Status Code", ex);
        final BindingResult result = ex.getBindingResult();

        final String error = result.getAllErrors().stream().map(e -> {
            if (e instanceof FieldError) {
                return new StringBuilder(((FieldError) e).getField()).append(" : ")
                        .append(e.getDefaultMessage()).toString();
            } else {
                return new StringBuilder(e.getObjectName()).append(" : ")
                        .append(e.getDefaultMessage()).toString();
            }
        }).collect(Collectors.joining(", "));
        return this.handleExceptionInternal(ex, new ApiResponse(false, error),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
