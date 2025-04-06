package com.vanana.yeogi.base.util;

import com.vanana.yeogi.base.config.properties.SmsProperties;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SmsUtil {
    private final SmsProperties properties;
    private final DefaultMessageService messageService;

    public SmsUtil(SmsProperties properties){
        this.properties= properties;
        this.messageService = NurigoApp.INSTANCE.initialize(properties.getKey(), properties.getSecret(), "https://api.coolsms.co.kr");
    }

    public String sendSms(String toNumber){
        String certCode = generateCertCode();

        Message message = new Message();
        message.setFrom(properties.getFromNumber());
        message.setTo(toNumber);
        message.setText("본인인증번호 [ " + certCode + " ]를 입력해주세요");

        this.messageService.sendOne(new SingleMessageSendingRequest(message));

        return certCode;
    }

    public String generateCertCode(){
        Random rand = new Random();
        StringBuilder randomNum = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String random = Integer.toString(rand.nextInt(10));
            randomNum.append(random);
        }

        return randomNum.toString();
    }
}
