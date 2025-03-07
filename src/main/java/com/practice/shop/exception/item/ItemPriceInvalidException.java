package com.practice.shop.exception.item;

public class ItemPriceInvalidException extends RuntimeException {
    public ItemPriceInvalidException() {
        super("아이템 가격은 0원 이상이어야 합니다.");
    }
}
