package com.practice.shop.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String displayName;
}
