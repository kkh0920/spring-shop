package com.practice.shop.exception.member;

public class IdDuplicateException extends RuntimeException {
    public IdDuplicateException() {
        super("이미 존재하는 아이디입니다.");
    }
}
