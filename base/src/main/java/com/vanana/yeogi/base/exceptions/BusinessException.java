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

    // argument 대신 map으로
    // log level 구분
    // 매개변수로 log level 같이
    // 원본 예외 있는 경우 같이 매개변수로 추가
    // 원본 예외를 커스텀 예외로 변경할 때 원본 예외 추가
    public BusinessException(ErrorType errorType, Object... args){
        this.errorType = errorType;
        this.args = args;
    }

}
