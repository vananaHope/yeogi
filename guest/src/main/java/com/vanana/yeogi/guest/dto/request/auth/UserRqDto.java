package com.vanana.yeogi.guest.dto.request.auth;

import com.vanana.yeogi.base.validate.GuestUserValidate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

    @NotBlank
    private String phoneNumber;

    @Override
    public boolean isPasswordMatching() {
        return password.equals(confirmPassword);
    }

    @Override
    public boolean checkPhoneNumber() {
        return false;
    }

    @Override
    public boolean isAdult() {
        return false;
    }


}
