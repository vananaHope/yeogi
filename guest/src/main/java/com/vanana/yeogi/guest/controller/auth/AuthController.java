package com.vanana.yeogi.guest.controller.auth;

import com.vanana.yeogi.base.dto.response.ApiResponse;
import com.vanana.yeogi.guest.dto.request.auth.UserRqDto;
import com.vanana.yeogi.guest.service.auth.LoginService;
import com.vanana.yeogi.guest.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AuthController {
    private final LoginService loginService;
    private final UserService userService;

    //TODO
    // - VALIDATOR 적용
    // - GlobalExceptionHandler 수정
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
        @ModelAttribute @Validated UserRqDto dto, BindingResult bindingResult
    )
    {
        userService.register(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("회원가입 완료"));
    }
}
