package com.practice.shop.exception.member;

public class IdBlankException extends RuntimeException {
    public IdBlankException() {
        super("아이디는 필수 입력값입니다.");
    }
}
