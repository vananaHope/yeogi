package com.vanana.yeogi.admin.service;

import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminTermsServiceTest {
    private AdminTermsRqDto adminTermsRqDto;

    @Autowired
    private AdminTermsService adminTermsService;

    @BeforeEach
    void setUp() {
        adminTermsRqDto = AdminTermsRqDto.builder()
                .title("만 14세 이상 확인")
                .content("개인정보 보호법에는 만 14세미만 아동의 개인정보 수집 시 법정대리인 동의를 받도록 규정하고 있으며, 만 14세 미만 아동이 법정대리인 동의없이 회원가입을 하는 경우 회원탈퇴 또는 서비스 이용이 제한될 수 있음을 알려드립니다.")
                .isMandatory(true)
                .isUseYn(true)
                .build();
    }

    @Test
    void createTerms() {
        AdminTermsRsDto termsRsDto = adminTermsService.addNewTerms(adminTermsRqDto);
        System.out.println("termsRsDto = " + termsRsDto);
    }

}
