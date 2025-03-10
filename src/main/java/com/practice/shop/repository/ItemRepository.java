package com.practice.shop.repository;

import com.practice.shop.domain.Item;
import com.practice.shop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByInsertDateDesc();

    List<Item> findAllByMemberOrderByInsertDateDesc(Member member);
}
