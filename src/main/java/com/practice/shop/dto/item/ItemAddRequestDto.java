package com.practice.shop.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ItemAddRequestDto {
    private String username;
    private String title;
    private Integer price;
}
