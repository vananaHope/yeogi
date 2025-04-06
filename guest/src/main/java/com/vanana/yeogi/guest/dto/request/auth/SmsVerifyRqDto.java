package com.vanana.yeogi.guest.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SmsVerifyRqDto(
        @NotBlank(message = "휴대폰 번호를 입력해주세요")
        String phoneNumber,

        @NotBlank(message = "인증번호를 입력해주세요")
        @Size(max = 4)
        String certCode
) {}
