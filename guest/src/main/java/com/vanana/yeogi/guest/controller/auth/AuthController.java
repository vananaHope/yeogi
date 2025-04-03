package com.vanana.yeogi.guest.controller.auth;

import com.vanana.yeogi.guest.service.auth.LoginService;
import com.vanana.yeogi.guest.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
    private final UserService userService;


}
