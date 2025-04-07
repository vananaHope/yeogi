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
    TERMS_UPDATE_ERROR(2001,HttpStatus.CONFLICT,"낙관적 락으로 인한 약관 업데이트 충돌 발생","약관 업데이트 실패"),
    TERMS_NOT_MATCH(2002,HttpStatus.BAD_REQUEST,"약관이 최신 버전이 아닙니다.","약관 버전 확인 필요"),

    // 휴대폰 인증 에러
    PHONE_EXCEED_LIMIT(3000,HttpStatus.FORBIDDEN,"휴대폰 인증번호 전송을 10초 후 다시 시도해주세요","인증번호 전송 실패"),
    PHONE_CERT_NOT_FOUND(3001,HttpStatus.FORBIDDEN,"휴대폰 인증번호가 존재하지 않습니다.","인증번호 검증 실패"),
    PHONE_CERT_NOT_MATCH(3002,HttpStatus.FORBIDDEN,"인증번호가 일치하지 않습니다.","인증번호 검증 실패"),

    // 회원가입 유효성 에러
    USER_PW_NOT_VALID(4000,HttpStatus.FORBIDDEN,"비밀번호는 영문자, 숫자, 특수문자를 포함해야합니다","비밀번호 유효성 검증 실패"),
    USER_PW_NOT_MATCH(4001,HttpStatus.FORBIDDEN,"비밀번호와 확인 비밀번호가 일치하지 않습니다","비밀번호 확인 필요"),
    USER_PHONE_NOT_VALID(4002,HttpStatus.FORBIDDEN,"휴대폰 번호 형식이 맞지 않습니다","휴대폰 번호 형식이 맞지 않습니다"),
    USER_ADULT_NOT_VALID(4003,HttpStatus.FORBIDDEN,"만 19세 이상만 가입 가능합니다","만 19세 이상만 가입 가능합니다"),
    USER_NICKNAME_NOT_VALID(4004,HttpStatus.FORBIDDEN,"닉네임은 영문,숫자,한글만 가능합니다","닉네임은 영문,숫자,한글만 가능합니다")


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
