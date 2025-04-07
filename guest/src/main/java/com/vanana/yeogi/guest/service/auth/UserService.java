package com.vanana.yeogi.guest.service.auth;

import com.vanana.yeogi.base.repository.auth.RegisterQueryRepository;
import com.vanana.yeogi.base.repository.auth.RegisterRepository;
import com.vanana.yeogi.guest.dto.request.auth.UserRqDto;
import com.vanana.yeogi.guest.validate.GuestUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RegisterRepository repository;
    private final RegisterQueryRepository registerQueryRepository;

    /**
     * 회원가입
     * @param rqDto
     */
    public void register(UserRqDto rqDto){

        GuestUserValidator validator = new GuestUserValidator(rqDto);

    }

}
