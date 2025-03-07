package com.practice.shop.exception.item;

public class ItemTitleBlankException extends RuntimeException {
    public ItemTitleBlankException() {
        super("아이템 이름은 공백일 수 없습니다.");
    }
}
