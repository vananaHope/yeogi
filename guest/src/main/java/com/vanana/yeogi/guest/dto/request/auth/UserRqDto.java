package com.vanana.yeogi.guest.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserRqDto{

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

    @NotNull(message = "생일을 입력해주세요")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthday;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Size(min = 2, max = 14, message = "닉네임은 2자 이상 14자 이하로 입력해주세요")
    private String nickName;

    @NotBlank(message = "성별을 선택해주세요")
    private String gender;

    /**
     * 사용자 동의한 약관 목록
     */
    @NotEmpty(message = "약관 동의 항목은 1개 이상 필수입니다")
    private List<UserAgreeRqDto> userAgreeRqDtoList;

}
