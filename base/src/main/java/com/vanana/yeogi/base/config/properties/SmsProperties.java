package com.vanana.yeogi.base.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:application-sms.properties")
public class SmsProperties {
    @Value("${coolsms.key}")
    private String key;

    @Value("${coolsms.secret}")
    private String secret;

    @Value("${coolsms.fromNumber}")
    private String fromNumber;
}
