package com.vanana.yeogi.guest.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SmsSendRqDto(
        @NotBlank(message = "휴대폰 번호를 입력해주세요")
        String phoneNumber
) {}
