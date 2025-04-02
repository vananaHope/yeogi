package com.vanana.yeogi.base.value;

import lombok.Getter;

@Getter
public enum UserType {

    ADMIN("관리자"),
    GUEST("손님"),
    PROVIDER("제공자"),
    ANONYMOUS("미인증 사용자")

    ;

    private final String description;

    UserType(String description){
        this.description = description;
    }

}
