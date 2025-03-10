package com.practice.shop.exception.member;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("회원가입이 필요합니다.");
    }
}
