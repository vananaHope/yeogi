package com.vanana.yeogi.guest.validate;

import com.vanana.yeogi.base.exceptions.CustomException;
import com.vanana.yeogi.base.exceptions.ErrorType;
import com.vanana.yeogi.base.validate.GuestUserValidate;
import com.vanana.yeogi.guest.dto.request.auth.UserRqDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class GuestUserValidator implements GuestUserValidate{

    //TODO
    // - SPRING VALIDATOR 변경

    private final UserRqDto dto;

    public void validate(){
        if (!isPasswordMatching()) {
            throw CustomException.builder()
                    .errorType(ErrorType.USER_PW_NOT_MATCH)
                    .build();
        }
        if (!isValidPassword()) {
            throw CustomException.builder()
                    .errorType(ErrorType.USER_PW_NOT_VALID)
                    .build();
        }
        if (!isPhoneNumber()) {
            throw CustomException.builder()
                    .errorType(ErrorType.USER_PHONE_NOT_VALID)
                    .build();
        }
        if (!isAdult()) {
            throw CustomException.builder()
                    .errorType(ErrorType.USER_ADULT_NOT_VALID)
                    .build();
        }
        if (!isValidNickname()) {
            throw CustomException.builder()
                    .errorType(ErrorType.USER_NICKNAME_NOT_VALID)
                    .build();
        }
    }

    @Override
    public boolean isPasswordMatching() {
        return dto.getPassword().equals(dto.getConfirmPassword());
    }

    @Override
    public boolean isValidPassword() {
        String regEx = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).*$";
        return Pattern.matches(regEx, dto.getPassword());
    }

    @Override
    public boolean isPhoneNumber() {
        String regEx = "(01[016789])(\\d{3,4})(\\d{4})";
        return Pattern.matches(regEx, dto.getPhoneNumber());
    }

    @Override
    public boolean isAdult() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = dto.getBirthday();

        int age = today.getYear() - birthday.getYear();

        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonth().getValue();

        int birthDayOfMonth = birthday.getDayOfMonth();
        int birthdayMonth = birthday.getMonth().getValue();

        if(birthdayMonth * 100 + birthDayOfMonth > currentMonth * 100 + currentDay){
            age--;
        }

        return age >= 19;
    }

    @Override
    public boolean isValidNickname() {
        String regEx = "^[a-zA-Z0-9가-힣]+$";
        return Pattern.matches(regEx, dto.getNickName());
    }
}
