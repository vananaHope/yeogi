package com.vanana.yeogi.base.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public enum ErrorType {

    // global 에러
    DEFAULT_ERROR(1000,HttpStatus.BAD_REQUEST, "잘못된 요청입니다. 다시 시도해주세요","잘못된 요청입니다. 다시 시도해주세요"),
    UNKNOWN_ERROR(1001,HttpStatus.INTERNAL_SERVER_ERROR,"알 수 없는 에러 발생, 다시 시도해주세요","알 수 없는 에러 발생, 다시 시도해주세요"),

    // 약관 에러
    TERMS_ERROR(2000,HttpStatus.NOT_FOUND,"약관을 찾을 수 없습니다","약관 누락"),
    TERMS_UPDATE_ERROR(2001,HttpStatus.CONFLICT,"낙관적 락으로 인한 약관 업데이트 충돌 발생","약관 업데이트 실패")

    ;

    private final int code;
    private final HttpStatus status;
    private final String internalMsg;
    private final String externalMsg;

    ErrorType(int code, HttpStatus status, String internalMsg, String externalMsg){
        this.code = code;
        this.status = status;
        this.internalMsg = internalMsg;
        this.externalMsg = externalMsg;
    }

    public String getInternalMsg(Map<String, Object> mapArgs){
        if (mapArgs.isEmpty()) return this.internalMsg;

        String result = this.internalMsg;

        for(String key : mapArgs.keySet()){
            result = result.replace("{" + key + "}", mapArgs.get(key).toString());
        }

        return result;
    }

    public String getExternalMsg(Map<String, Object> mapArgs){
        if (mapArgs.isEmpty()) return this.externalMsg;

        String result = this.externalMsg;

        for(String key : mapArgs.keySet()){
            result = result.replace("{" + key + "}", mapArgs.get(key).toString());
        }

        return result;
    }

}
