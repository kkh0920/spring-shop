package com.practice.shop.exception.member;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException() {
        super("로그인이 필요한 서비스입니다.");
    }
}
