package com.vanana.yeogi.base.exceptions;

import com.vanana.yeogi.base.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //TODO
    // - MethodArgumentNotValidException 처리 추가

    /**
     * CustomerException 처리
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException ex){
        ErrorType errorType = ex.getErrorType();

        String internalMsg = errorType.getInternalMsg(ex.getMapArgs());
        String externalMsg = errorType.getExternalMsg(ex.getMapArgs());

        switch (ex.getLogLevel()){
            case DEBUG -> log.debug(internalMsg, ex.getOrgException(), ex);
            case INFO -> log.info(internalMsg, ex.getOrgException(), ex);
            case WARN -> log.warn(internalMsg, ex.getOrgException(), ex);
            case ERROR -> log.error(internalMsg, ex.getOrgException(), ex);
        }

        return ResponseEntity
                .status(errorType.getStatus())
                .body(ApiResponse.fail(errorType.getCode(), externalMsg));
    }

    /**
     * 모든 미확인 에러 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex){
        return ResponseEntity
                .status(ErrorType.UNKNOWN_ERROR.getStatus())
                .body(ApiResponse.fail(ErrorType.UNKNOWN_ERROR.getCode(), ErrorType.UNKNOWN_ERROR.getExternalMsg()));
    }
}
