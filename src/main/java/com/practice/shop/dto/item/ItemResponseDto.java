package com.practice.shop.dto.item;

import com.practice.shop.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponseDto {
    private Long id;
    private String title;
    private Integer price;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
    private String username;

    public static ItemResponseDto from(Item item) {
        return new ItemResponseDto(item.getId(), item.getTitle(), item.getPrice(),
                item.getInsertDate(), item.getUpdateDate(), item.getMember().getUsername());
    }
}
