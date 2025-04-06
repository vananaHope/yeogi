package com.vanana.yeogi.base.repository.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class SmsRepository {
    private final String SMS_PREFIX = "sms:";   // SMS PREFIX
    private final String SMS_LIMIT_PREFIX = "sms:limit:";
    private final int LIMIT_TIME = 3 * 60;      // 인증 유효 시간 3분
    private final int LIMIT_TRY_TIME = 10;      // 재요청 시간 제한 10초

    private final StringRedisTemplate redisTemplate;

    /**
     * 휴대폰 인증번호 저장
     * @param phoneNumber 휴대폰 인증번호 전송한 번호
     * @param code        휴대폰 인증번호
     */
    public void saveCertCode(String phoneNumber, String code){
        redisTemplate.opsForValue()
                .set(SMS_PREFIX + phoneNumber, code, LIMIT_TIME, TimeUnit.SECONDS);
    }

    /**
     * 휴대폰 인증번호 조회
     * @param phoneNumber 휴대폰 인증번호 전송한 번호
     */
    public String getSmsCertCode(String phoneNumber){
        return redisTemplate.opsForValue()
                .get(SMS_PREFIX + phoneNumber);
    }

    /**
     * 휴대폰 인증 완료 시 인증번호 삭제
     * @param phoneNumber 휴대폰 번호
     */
    public void deleteCertCode(String phoneNumber){
        redisTemplate.delete(SMS_PREFIX + phoneNumber);
    }

    /**
     * 휴대폰 인증번호 존재하는 지 확인
     * @param phoneNumber 휴대폰 번호
     */
    public boolean hasCertCode(String phoneNumber){
        return redisTemplate.hasKey(SMS_PREFIX + phoneNumber);
    }

    /**
     * 휴대폰 인증 제한 시간 정보 저장
     */
    public void saveCertLimit(String phoneNumber){
        redisTemplate.opsForValue()
                .set(SMS_LIMIT_PREFIX + phoneNumber, "LIMIT", LIMIT_TRY_TIME, TimeUnit.SECONDS);
    }

    /**
     * 휴대폰 인증 제한 시간 정보 확인
     * @param phoneNumber 휴대폰 번호
     */
    public boolean hasCertLimit(String phoneNumber){
        return redisTemplate.hasKey(SMS_LIMIT_PREFIX + phoneNumber);
    }
}
