package com.vanana.yeogi.admin.controller;

import com.vanana.yeogi.admin.dto.AdminListRsDto;
import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import com.vanana.yeogi.admin.service.AdminTermsService;
import com.vanana.yeogi.base.dto.response.ApiResponse;
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
     * 관리자 약관 전체 조회
     */
    @GetMapping("/terms")
    public ResponseEntity<ApiResponse<AdminListRsDto>> getAllTerms(){
        AdminListRsDto allTerms = adminTermsService.getAllTerms();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(allTerms));
    }

    /**
     * 관리자 약관 저장
     * @param adminTermsRqDto 관리자 약관 요청 DTO
     */
    @PostMapping("/terms")
    public ResponseEntity<ApiResponse<AdminTermsRsDto>> addNewTerms(
            @NotNull @RequestBody AdminTermsRqDto adminTermsRqDto
    ) {
        AdminTermsRsDto adminTermsRsDto = adminTermsService.addNewTerms(adminTermsRqDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(adminTermsRsDto));
    }

    /**
     * 관리자 약관 수정
     * @param dto 관리자 약관 요청 dto
     */
    @PutMapping("/terms/{terms_id}")
    public ResponseEntity<ApiResponse<AdminTermsRsDto>> updateTerms(
            @NotNull @RequestBody AdminTermsRqDto dto){
        AdminTermsRsDto modified = adminTermsService.updateTerms(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(modified));
    }
}
