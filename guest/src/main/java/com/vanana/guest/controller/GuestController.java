package com.vanana.guest.controller;

import com.vanana.guest.dto.GuestApiResponse;
import com.vanana.guest.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class GuestController {

    private final TermsService termsService;

}
