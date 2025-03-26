package com.vanana.yeogi.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminApiResponse<T> {
    private boolean result;
    private String message;
    private T data;

    public static <T> AdminApiResponse<T> success(T data) {
        return AdminApiResponse.<T>builder()
                .result(true)
                .message(null)
                .data(data)
                .build();
    }

    // Builder-based fail response
    public static <T> AdminApiResponse<T> fail(String message) {
        return AdminApiResponse.<T>builder()
                .result(false)
                .data(null)
                .message(message)
                .build();
    }
}
