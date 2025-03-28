package com.vanana.yeogi.admin.service;

import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.repository.TermsRepository;
import jakarta.persistence.OptimisticLockException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AdminTermsServiceTest {
    private AdminTermsRqDto adminTermsRqDto;
    @Autowired
    private AdminTermsService adminTermsService;
    @Autowired
    private TermsRepository termsRepository;

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

    @Test
    void updateTerms(){
        List<AdminTermsRsDto> allTerms = adminTermsService.getAllTerms();
        assertThat(10).isEqualTo(allTerms.size());

        AdminTermsRsDto dto = allTerms.get(0);

        adminTermsService.updateTerms(dto.termsId(), adminTermsRqDto);

        List<AdminTermsRsDto> dtos = adminTermsService.getAllTerms();
        dtos.forEach(System.out::println);
    }

    @Test
    @DisplayName("업데이트 동시성 테스트")
    void updateLockTest() throws InterruptedException {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch latch = new CountDownLatch(threadCount);

        Long termsId = 1L;
        AdminTermsRqDto updateDto = new AdminTermsRqDto("수정 약관 제목", "수정 내용 약관", true, true);

        for(int i = 0; i < threadCount; i++){
            executorService.submit(()->{
                try{
                    adminTermsService.updateTerms(termsId, updateDto);
                }catch(OptimisticLockException e){
                    System.err.println("Lock conflict: " + e.getMessage());
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        TermsEt updated = termsRepository.findById(termsId).orElseThrow();
        System.out.println("update termsEt = " + updated);

        assertThat(updated.getVersion()).isEqualTo(1);
    }
}
