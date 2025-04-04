package com.vanana.yeogi.base.exceptions;

import com.vanana.yeogi.base.value.LogLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorType errorType;
    private final Exception orgException;
    private final LogLevel logLevel;
    private final Map<String, Object> mapArgs;

    @Builder
    public CustomException(ErrorType errorType,
                           LogLevel logLevel,
                           Exception orgException,
                           Map<String, Object> mapArgs
    ){
        this.errorType = errorType != null ? errorType : ErrorType.DEFAULT_ERROR;
        this.logLevel = logLevel != null ? logLevel : LogLevel.INFO;
        this.orgException = orgException != null ? orgException : this;
        this.mapArgs = mapArgs != null ? mapArgs : Map.of();
    }

}
