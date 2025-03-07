package com.practice.shop.exception.member;

public class PasswordBlankException extends RuntimeException {
    public PasswordBlankException() {
        super("비밀번호는 필수 입력값입니다.");
    }
}
