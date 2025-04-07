package com.vanana.yeogi.guest.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vanana.yeogi.base.validate.GuestUserValidate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@RequiredArgsConstructor
public class UserRqDto implements GuestUserValidate {

    @NotBlank(message = "이메일 주소를 입력해주세요")
    @Email(message = "올바른 이메일 주소를 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요")
    private String password;

    @NotBlank(message = "비밀번호를 확인해주세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요")
    private String confirmPassword;

    @NotBlank(message = "휴대폰 번호를 입력해주세요")
    @Size(max = 11)
    private String phoneNumber;

    @NotBlank(message = "생일을 입력해주세요")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "Asia/Seoul")
    private LocalDate birthday;

    /**
     * 사용자 동의한 약관 목록
     */
    private List<UserAgreeRqDto> userAgreeRqDtoList;

    /**
     * 회원가입 Guest 체크로직
     */
    @Override
    public boolean isPasswordMatching() {
        return password.equals(confirmPassword);
    }

    @Override
    public boolean isPhoneNumber() {
        String regEx = "(01[016789])(\\d{3,4})(\\d{4})";
        return Pattern.matches(regEx, phoneNumber);
    }

    @Override
    public boolean isAdult() {
        LocalDate today = LocalDate.now();

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


}
