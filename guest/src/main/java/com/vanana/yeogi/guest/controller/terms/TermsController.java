package com.vanana.yeogi.guest.controller.terms;


import com.vanana.yeogi.base.dto.response.ApiResponse;
import com.vanana.yeogi.guest.dto.response.terms.TermsListRsDto;
import com.vanana.yeogi.guest.service.terms.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class TermsController {
    private final TermsService termsService;

    /**
     * 최신 버전 약관 목록 조회
     */
    @GetMapping("/terms")
    public ResponseEntity<ApiResponse<TermsListRsDto>> getAllRecentTerms() {
        TermsListRsDto recentTerms = termsService.getAllRecentTerms();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(recentTerms));
    }

    /**
     * 약관 상세내용 조회
     * @param title 약관 키 값
     */
    @GetMapping("/terms/detail")
    public ResponseEntity<ApiResponse<TermsListRsDto>> getRecentTermsDetail(
            @RequestParam(name = "title") String title){
        TermsListRsDto termsDetail = termsService.getRecentTermsDetail(title);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(termsDetail));
    }

}
