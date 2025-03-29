package com.vanana.yeogi.base.exceptions;

import com.vanana.yeogi.base.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //TODO
    //  - AccssFilter 구현
    //  - 요청 정보에서 언어 정보 가져오기
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex){
        String message = "";
        HttpStatus status;

        try{
            if(ex.getErrorType() == null){
                message = ex.getMessage();
                status = ErrorType.DEFAULT_ERROR.getStatus();
            }else{
                ErrorType errorType = ex.getErrorType();
                Object[] args = ex.getArgs();

                message = errorType.getMessage("KOREAN",args);
                status = errorType.getStatus();
            }
        }catch(Exception e){
            message = ErrorType.DEFAULT_ERROR.getMessage("KOREAN");
            status = ErrorType.DEFAULT_ERROR.getStatus();
        }

        return ResponseEntity
                .status(status)
                .body(ApiResponse.fail(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(ex.getMessage()));
    }
}
