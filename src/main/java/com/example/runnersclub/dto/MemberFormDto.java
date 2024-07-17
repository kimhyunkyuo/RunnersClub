package com.example.runnersclub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotEmpty(message = "이메일 입력해주세요")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;
    @NotEmpty(message = "비빌번호를 입력해주세요")
    private String password;

    @NotEmpty(message = "주소를 입력해주세요")
    private String zipcode;

    private String streetadr;

    private String detailadr;
}
