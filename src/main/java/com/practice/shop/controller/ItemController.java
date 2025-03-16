package com.practice.shop.controller;

import com.practice.shop.dto.Pager;
import com.practice.shop.dto.item.ItemAddRequestDto;
import com.practice.shop.dto.item.ItemDetailRequestDto;
import com.practice.shop.dto.item.ItemModifyRequestDto;
import com.practice.shop.dto.item.ItemResponseDto;
import com.practice.shop.service.ItemService;
import com.practice.shop.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URL;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final S3Service s3Service;

    /* ------------------ 페이지 조회 (DB 데이터 조회) ------------------ */

    @GetMapping("/")
    public String homePage() {
        return "redirect:/item/page/1";
    }

    @GetMapping("/item")
    public String itemPage() {
        return "redirect:/item/page/1";
    }

    @GetMapping("/item/page/{page}")
    public String itemPage(@PathVariable Integer page, Model model) {
        Page<ItemResponseDto> items = itemService.findPageBy(page - 1, 5);
        Pager pager = new Pager(items.getTotalPages(), page, 5);

        model.addAttribute("items", items);
        model.addAttribute("pager", pager);

        return "/item/item";
    }

    @GetMapping("/item/detail/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        ItemDetailRequestDto itemDetailRequestDto = new ItemDetailRequestDto(id);
        ItemResponseDto item = itemService.showDetail(itemDetailRequestDto);
        model.addAttribute("itemDetail", item);
        return "/item/item_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/item/write")
    public String writePage() {
        return "/item/item_write";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/item/modify/{id}")
    public String modifyPage(@PathVariable Long id, Model model) {
        ItemResponseDto item = itemService.findOne(id);
        model.addAttribute("item", item);
        return "item/item_modify";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/item/upload")
    public ResponseEntity<URL> getUploadURL(@RequestParam String filename) {
        URL url = s3Service.generatePresignedUploadUrl(filename);
        return ResponseEntity.ok(url);
    }

    /* ------------------ DB에 직접적으로 수정을 가하는 부분 ------------------ */

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/item/add")
    public ResponseEntity<Void> addItem(@RequestBody ItemAddRequestDto itemAddRequestDto,
                                        Authentication auth) {
        itemAddRequestDto.setUsername(auth.getName());
        itemService.addItem(itemAddRequestDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/item/modify")
    public ResponseEntity<Void> modifyItem(@RequestBody ItemModifyRequestDto itemModifyRequestDto) {
        itemService.modifyItem(itemModifyRequestDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/item/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
