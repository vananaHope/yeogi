package com.vanana.yeogi.guest.controller.auth;

import com.vanana.yeogi.base.dto.response.ApiResponse;
import com.vanana.yeogi.guest.dto.request.auth.SmsSendRqDto;
import com.vanana.yeogi.guest.dto.request.auth.SmsVerifyRqDto;
import com.vanana.yeogi.guest.service.auth.SmsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sms")
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<String>> sendSms(
            @NotNull @Valid @RequestBody SmsSendRqDto dto
    ){
        smsService.sendSms(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("인증번호 전송 완료"));
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<String>> verifySms(
            @NotNull @Valid @RequestBody SmsVerifyRqDto dto
    ){
        smsService.verifySms(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("인증번호 검증 완료"));
    }

}
