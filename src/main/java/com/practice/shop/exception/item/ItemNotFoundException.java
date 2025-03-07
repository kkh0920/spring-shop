package com.practice.shop.exception.item;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("해당 아이템은 존재하지 않습니다.");
    }
}
