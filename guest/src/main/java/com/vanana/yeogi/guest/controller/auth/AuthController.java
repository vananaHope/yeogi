package com.vanana.yeogi.guest.controller.auth;

import com.vanana.yeogi.base.dto.response.ApiResponse;
import com.vanana.yeogi.guest.dto.request.auth.UserRqDto;
import com.vanana.yeogi.guest.service.auth.LoginService;
import com.vanana.yeogi.guest.service.auth.UserService;
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
@RequestMapping("/api/user")
public class AuthController {
    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> userRegister(
        @NotNull @Valid @RequestBody UserRqDto dto
    )
    {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("회원가입 완료"));
    }
}
