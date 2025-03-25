package com.vanana.yeogi.guest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestApiResponse<T> {
    private boolean result;
    private String message;
    private T data;

    public static <T> GuestApiResponse<T> success(T data) {
        return GuestApiResponse.<T>builder()
                .result(true)
                .message(null)
                .data(data)
                .build();
    }

    // Builder-based fail response
    public static <T> GuestApiResponse<T> fail(String message) {
        return GuestApiResponse.<T>builder()
                .result(false)
                .data(null)
                .message(message)
                .build();
    }
}
