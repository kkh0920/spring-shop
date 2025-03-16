package com.practice.shop.exception.item;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException() {
        super("해당 페이지를 찾을 수 없습니다.");
    }
}
