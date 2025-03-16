package com.practice.shop.service;

import com.practice.shop.domain.Item;
import com.practice.shop.domain.Member;
import com.practice.shop.dto.item.ItemAddRequestDto;
import com.practice.shop.dto.item.ItemDetailRequestDto;
import com.practice.shop.dto.item.ItemModifyRequestDto;
import com.practice.shop.dto.item.ItemResponseDto;
import com.practice.shop.exception.item.ItemNotFoundException;
import com.practice.shop.exception.item.ItemPriceInvalidException;
import com.practice.shop.exception.item.ItemTitleBlankException;
import com.practice.shop.exception.item.PageNotFoundException;
import com.practice.shop.exception.member.UserNotFoundException;
import com.practice.shop.repository.ItemRepository;
import com.practice.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public void addItem(ItemAddRequestDto itemAddRequestDto) {
        Optional<Member> member = memberRepository.findByUsername(itemAddRequestDto.getUsername());
        if(member.isEmpty()) {
            throw new UserNotFoundException();
        }
        Item item = new Item();
        item.setTitle(itemAddRequestDto.getTitle());
        item.setPrice(itemAddRequestDto.getPrice());
        item.setMember(member.get());
        itemValidation(item);
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public ItemResponseDto showDetail(ItemDetailRequestDto itemDetailRequestDto) {
        return findOne(itemDetailRequestDto.getId());
    }

    public void modifyItem(ItemModifyRequestDto itemModifyRequestDto) throws ItemNotFoundException {
        Optional<Item> item = itemRepository.findById(itemModifyRequestDto.getId());
        if(item.isEmpty()) {
            throw new ItemNotFoundException();
        }

        Item itemEntity = item.get();
        itemEntity.setTitle(itemModifyRequestDto.getTitle());
        itemEntity.setPrice(itemModifyRequestDto.getPrice());
        itemValidation(itemEntity);

        itemRepository.save(itemEntity);
    }

    public ItemResponseDto findOne(Long id) throws ItemNotFoundException {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            throw new ItemNotFoundException();
        }
        Item itemEntity = item.get();
        return ItemResponseDto.from(itemEntity);
    }

    public List<ItemResponseDto> findAll() {
        return itemRepository.findAllByOrderByInsertDateDesc()
                .stream()
                .map(ItemResponseDto::from)
                .collect(Collectors.toList());
    }

    public Page<ItemResponseDto> findPageBy(Integer page, Integer pageSize) {
        if(page < 0) {
            throw new PageNotFoundException();
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("insertDate").descending());
        Page<ItemResponseDto> dto = itemRepository.findAll(pageable).map(ItemResponseDto::from);
        if (dto.getTotalPages() < page) {
            throw new PageNotFoundException();
        }
        return dto;
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
