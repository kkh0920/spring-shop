package com.practice.shop.service;

import com.practice.shop.domain.Item;
import com.practice.shop.dto.item.ItemAddRequestDto;
import com.practice.shop.dto.item.ItemDetailRequestDto;
import com.practice.shop.dto.item.ItemModifyRequestDto;
import com.practice.shop.exception.item.ItemNotFoundException;
import com.practice.shop.exception.item.ItemPriceInvalidException;
import com.practice.shop.exception.item.ItemTitleBlankException;
import com.practice.shop.exception.member.UserNotAuthenticatedException;
import com.practice.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final GlobalAuthenticationConfigurerAdapter enableGlobalAuthenticationAutowiredConfigurer;

    public void addItem(ItemAddRequestDto itemAddRequestDto) {
        Item item = new Item();
        item.setTitle(itemAddRequestDto.getTitle());
        item.setPrice(itemAddRequestDto.getPrice());
        itemValidation(item);

        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item showDetail(ItemDetailRequestDto itemDetailRequestDto) {
        return findOne(itemDetailRequestDto.getId());
    }

    public void modifyItem(ItemModifyRequestDto itemModifyRequestDto) {
        Item item = findOne(itemModifyRequestDto.getId());
        item.setTitle(itemModifyRequestDto.getTitle());
        item.setPrice(itemModifyRequestDto.getPrice());
        itemValidation(item);

        itemRepository.save(item);
    }

    public Item findOne(Long id) throws ItemNotFoundException {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            throw new ItemNotFoundException();
        }
        return item.get();
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    private void itemValidation(Item item) {
        String title = item.getTitle();
        Integer price = item.getPrice();
        if(title == null || title.isEmpty()) {
            throw new ItemTitleBlankException();
        } else if(price == null || price < 0) {
            throw new ItemPriceInvalidException();
        }
    }
}
