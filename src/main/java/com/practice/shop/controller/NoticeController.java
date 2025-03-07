package com.practice.shop.controller;

import com.practice.shop.domain.Notice;
import com.practice.shop.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(Model model) {
        List<Notice> notices = noticeService.findAll();
        model.addAttribute("notices", notices);
        return "notice";
    }
}