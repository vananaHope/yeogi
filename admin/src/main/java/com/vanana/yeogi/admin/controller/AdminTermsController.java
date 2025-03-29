package com.vanana.yeogi.admin.controller;

import com.vanana.yeogi.admin.dto.AdminApiResponse;
import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import com.vanana.yeogi.admin.service.AdminTermsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminTermsController {
    private final AdminTermsService adminTermsService;

    /**
     * 관리자 약관 전체 조회
     */
    @GetMapping("/terms")
    public ResponseEntity<AdminApiResponse<?>> getAllTerms(){
        List<AdminTermsRsDto> allTerms = adminTermsService.getAllTerms();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AdminApiResponse.success(allTerms));
    }

    /**
     * 관리자 약관 저장
     * @param adminTermsRqDto 관리자 약관 요청 DTO
     */
    @PostMapping("/terms")
    public ResponseEntity<AdminApiResponse<?>> addNewTerms(
            @NotNull @RequestBody AdminTermsRqDto adminTermsRqDto
    ) {
        AdminTermsRsDto adminTermsRsDto = adminTermsService.addNewTerms(adminTermsRqDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AdminApiResponse.success(adminTermsRsDto));
    }

    /**
     * 관리자 약관 수정
     * @param termsId 약관 id
     * @param dto 관리자 약관 요청 dto
     * @return
     */
    @PutMapping("/terms/{terms_id}")
    public ResponseEntity<AdminApiResponse<?>> updateTerms(
            @PathVariable(name = "terms_id") Long termsId,
            @NotNull @RequestBody AdminTermsRqDto dto){
        AdminTermsRsDto modified = adminTermsService.updateTerms(termsId, dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AdminApiResponse.success(modified));
    }
}
