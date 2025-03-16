package com.practice.shop.repository;

import com.practice.shop.domain.Item;
import com.practice.shop.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByInsertDateDesc();

    Page<Item> findAllByMember(Member member, Pageable pageable);
}
