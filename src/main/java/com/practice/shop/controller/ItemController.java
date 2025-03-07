package com.practice.shop.controller;

import com.practice.shop.domain.Item;
import com.practice.shop.dto.item.ItemAddRequestDto;
import com.practice.shop.dto.item.ItemDetailRequestDto;
import com.practice.shop.dto.item.ItemModifyRequestDto;
import com.practice.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /* ------------------ 페이지 조회 (DB 데이터 조회) ------------------ */

    @GetMapping("/")
    public String homePage() { // 임시 메인 페이지
        return "redirect:/item";
    }

    @GetMapping("/item")
    public String itemPage(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "/item/item";
    }

    @GetMapping("/item/write")
    public String writePage() {
        return "/item/item_write";
    }

    @GetMapping("/item/detail/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        ItemDetailRequestDto itemDetailRequestDto = new ItemDetailRequestDto(id);
        Item item = itemService.showDetail(itemDetailRequestDto);
        model.addAttribute("itemDetail", item);
        return "/item/item_detail";
    }

    @GetMapping("/item/modify/{id}")
    public String modifyPage(@PathVariable Long id, Model model) {
        Item item = itemService.findOne(id);
        model.addAttribute("item", item);
        return "item/item_modify";
    }

    /* ------------------ DB에 직접적으로 수정을 가하는 부분 ------------------ */

    @PostMapping("/item/add")
    public ResponseEntity<Void> addItem(@RequestBody ItemAddRequestDto itemAddRequestDto) {
        itemService.addItem(itemAddRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/item/modify")
    public ResponseEntity<Void> modifyItem(@RequestBody ItemModifyRequestDto itemModifyRequestDto) {
        itemService.modifyItem(itemModifyRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/item/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
