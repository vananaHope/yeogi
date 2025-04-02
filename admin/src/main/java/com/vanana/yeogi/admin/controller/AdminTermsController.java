package com.vanana.yeogi.admin.controller;

import com.vanana.yeogi.admin.dto.response.AdminListRsDto;
import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.service.AdminTermsService;
import com.vanana.yeogi.base.dto.response.ApiResponse;
import com.vanana.yeogi.base.entity.embeddable.TermsId;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminTermsController {
    private final AdminTermsService adminTermsService;

    /**
     * 관리자 약관 전체 목록 조회
     */
    @GetMapping("/terms")
    public ResponseEntity<ApiResponse<AdminListRsDto>> getAllTerms(){
        AdminListRsDto allTerms = adminTermsService.getAllTerms();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(allTerms));
    }

    /**
     * 관리자 약관 상세 내용 및 버전 리스트 조회
     */


    /**
     * 관리자 약관 저장
     * @param adminTermsRqDto 관리자 약관 요청 DTO
     */
    @PostMapping("/terms")
    public ResponseEntity<ApiResponse<TermsId>> addNewTerms(
            @NotNull @RequestBody AdminTermsRqDto adminTermsRqDto
    ) {
        TermsId termsId = adminTermsService.addNewTerms(adminTermsRqDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(termsId));
    }

    /**
     * 관리자 약관 수정
     * @param dto 관리자 약관 요청 dto
     */
    @PutMapping("/terms")
    public ResponseEntity<ApiResponse<TermsId>> updateTerms(
            @NotNull @RequestBody AdminTermsRqDto dto)
    {
        TermsId termsId = adminTermsService.updateTerms(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(termsId));
    }
}
