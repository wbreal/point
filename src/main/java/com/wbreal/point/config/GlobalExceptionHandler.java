package com.wbreal.point.config;

import com.wbreal.point.model.response.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // @ExceptionHandler({Exception.class, RuntimeException.class})
    @ExceptionHandler
    protected final ResponseEntity<?> handleAllException(Exception exception) {
        log.error("RestController 관점으로 예외를 잡아서 제어할 수 있는 핸들러 입니다. [{}]", exception.getMessage(), exception);
        // TODO : 상호(B2B...등) 규약에 따라 예외에 대한 리스펀스를 사용자 정의할 수 있습니다.
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .Message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
