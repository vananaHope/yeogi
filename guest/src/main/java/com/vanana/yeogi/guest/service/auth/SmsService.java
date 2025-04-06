package com.vanana.yeogi.guest.service.auth;

import com.vanana.yeogi.base.exceptions.CustomException;
import com.vanana.yeogi.base.exceptions.ErrorType;
import com.vanana.yeogi.base.repository.auth.SmsRepository;
import com.vanana.yeogi.base.util.SmsUtil;
import com.vanana.yeogi.base.value.LogLevel;
import com.vanana.yeogi.guest.dto.request.auth.SmsSendRqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsUtil smsUtil;
    private final SmsRepository smsRepository;

    /**
     * 휴대폰 인증번호 전송
     */
    public void sendSms(SmsSendRqDto rqDto){
        String phoneNumber = rqDto.phoneNumber();

        // 10초 내 인증번호 전송 재시도 시 CustomException 처리
        if(smsRepository.hasCertLimit(phoneNumber)){
            throw CustomException.builder()
                    .errorType(ErrorType.PHONE_EXCEED_LIMIT)
                    .logLevel(LogLevel.WARN)
                    .build();
        }

        // 10초 후 인증번호 전송 재시도 시 기존 인증번호 제거 후 다시 전송
        if(smsRepository.hasCertCode(phoneNumber)){
            smsRepository.deleteCertCode(phoneNumber);
        }

        String sendCertCode = smsUtil.sendSms(rqDto.phoneNumber());
        smsRepository.saveCertLimit(phoneNumber);
        smsRepository.saveCertCode(phoneNumber, sendCertCode);
    }
}
