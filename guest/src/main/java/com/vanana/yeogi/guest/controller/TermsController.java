package com.vanana.yeogi.guest.controller;

import com.vanana.yeogi.guest.dto.GuestApiResponse;
import com.vanana.yeogi.guest.dto.response.TermsRsDto;
import com.vanana.yeogi.guest.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class TermsController {
    private final TermsService termsService;

    @GetMapping("/terms")
    public ResponseEntity<GuestApiResponse<?>> getAllRecentTerms() {
        List<TermsRsDto> recentTerms = termsService.getAllRecentTerms();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GuestApiResponse.success(recentTerms));
    }

    @GetMapping("/terms/{id}")
    public ResponseEntity<GuestApiResponse<?>> getRecentTermsDetail(@PathVariable Long id){
        List<TermsRsDto> termsDetail = termsService.getRecentTermsDetail(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GuestApiResponse.success(termsDetail));
    }

}
