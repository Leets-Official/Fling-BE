package com.fling.fllingbe.global.error.exception;

import com.fling.fllingbe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";
    @ExceptionHandler(ServiceException.class)
    public ResponseDto handleApplicationException(ServiceException ex) {
        log.error(LOG_FORMAT, ex.getClass().getSimpleName(), ex.getErrorCode(), ex.getMessage());
        return ResponseDto.of(ex.getErrorCode(), ex.getMessage());
    }
}
