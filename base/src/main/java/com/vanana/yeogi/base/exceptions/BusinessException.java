package com.vanana.yeogi.base.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private ErrorType errorType;
    private Object[] args;

    private BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(ErrorType errorType, Object... args){
        this.errorType = errorType;
        this.args = args;
    }

}
