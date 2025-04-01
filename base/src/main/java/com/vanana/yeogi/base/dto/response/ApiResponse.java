package com.vanana.yeogi.base.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean result;
    private String message;
    private int code;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .result(true)
                .data(data)
                .message(null)
                .build();
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        return ApiResponse.<T>builder()
                .result(false)
                .data(null)
                .code(code)
                .message(message)
                .build();
    }
}
