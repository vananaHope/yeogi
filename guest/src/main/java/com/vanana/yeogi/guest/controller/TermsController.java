package com.vanana.yeogi.guest.controller;


import com.vanana.yeogi.base.dto.response.ApiResponse;
import com.vanana.yeogi.guest.dto.GuestListRsDto;
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

    /**
     * 최신 버전 약관 목록 조회
     * @return
     */
    @GetMapping("/terms")
    public ResponseEntity<ApiResponse<GuestListRsDto>> getAllRecentTerms() {
        GuestListRsDto recentTerms = termsService.getAllRecentTerms();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(recentTerms));
    }

    /**
     * 약관 상세내용 조회
     * @param termsId 약관 키 값
     * @return
     */
    @GetMapping("/terms/{terms_id}")
    public ResponseEntity<ApiResponse<GuestListRsDto>> getRecentTermsDetail(
            @PathVariable(name = "terms_id") Long termsId){
        GuestListRsDto termsDetail = termsService.getRecentTermsDetail(termsId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(termsDetail));
    }

}
