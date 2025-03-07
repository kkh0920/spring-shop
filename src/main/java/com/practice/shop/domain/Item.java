package com.practice.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@ToString
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;
}
