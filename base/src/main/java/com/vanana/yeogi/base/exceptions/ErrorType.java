package com.vanana.yeogi.base.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public enum ErrorType {
    // 다국어 할 때 i18n
    // global 에러
    DEFAULT_ERROR(HttpStatus.BAD_REQUEST,
            new Messages()
                    .en("Bad Request")
                    .ko("잘못된 요청")
    ),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            new Messages()
                    .en("UNKNOWN ERROR")
                    .ko("알 수 없는 오류")
    ),

    // 약관 에러
    TERMS_ERROR(HttpStatus.NOT_FOUND,
            new Messages()
                    .en("Terms Not Found")
                    .ko("약관 데이터가 누락되었습니다.")
    ),
    TERMS_UPDATE_ERROR(HttpStatus.CONFLICT,
            new Messages()
                    .en("Terms Conflict detected, This terms was already updated")
                    .ko("해당 약관이 이미 수정되었습니다. 다시 시도해주세요")
    )

    ;

    // 내부적으로만 쓰는 코드, 메시지를 internal(상세), external(두루뭉술) 구분, 성공일 떄는 0 0 아니면 실패
    // 응답할 때는 굳이 결과 보내지 않고 코드로 상태코드 200일 때는 성공이라서 굳이 반환값 x
    @Getter
    private final HttpStatus status;
    private final Messages messages;

    ErrorType(HttpStatus status, Messages messages){
        this.status = status;
        this.messages = messages;
    }

    //TODO
    //  - String -> Language Enum 타입으로 변경
    //  - Default getMessage 메서드 생성
    public String getMessage(String language, Object... args){
        String message;

        if("ENGLISH".equals(language)){
            message = messages.messageMap.get("ENGLISH");
        } else if ("KOREAN".equals(language)) {
            message = messages.messageMap.get("KOREAN");
        }else {
            message = messages.messageMap.get("ENGLISH");
        }

        return MessageFormat.format(message, args);
    }

    private static class Messages{
        private final Map<String, String> messageMap = new HashMap<>();

        private Messages en(String msg){
            messageMap.put("ENGLISH", msg);
            return this;
        }

        private Messages ko(String msg){
            messageMap.put("KOREAN", msg);
            return this;
        }
    }
}
