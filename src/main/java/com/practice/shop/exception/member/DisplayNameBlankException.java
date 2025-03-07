package com.practice.shop.exception.member;

public class DisplayNameBlankException extends RuntimeException {
    public DisplayNameBlankException() {
        super("닉네임은 필수 입력값입니다.");
    }
}
