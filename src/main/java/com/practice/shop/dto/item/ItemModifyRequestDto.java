package com.practice.shop.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemModifyRequestDto {
    private Long id;
    private String title;
    private Integer price;
}
