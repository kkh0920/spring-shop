package com.practice.shop.controller;

import com.practice.shop.dto.MyUserDetails;
import com.practice.shop.dto.Pager;
import com.practice.shop.dto.item.ItemResponseDto;
import com.practice.shop.dto.member.SignUpRequestDto;
import com.practice.shop.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage() {
        return "/login/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-up")
    public String signUpPage() {
        return "/login/sign_up";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my-page")
    public String myPage() {
        return "redirect:/my-page/1";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my-page/{page}")
    public String myPage(@PathVariable Integer page, Authentication auth, Model model) {
        MyUserDetails details = (MyUserDetails) auth.getPrincipal();
        Page<ItemResponseDto> items = memberService.myPage(page - 1, 5, auth.getName());
        Pager pager = new Pager(items.getTotalPages(), page, 5);

        model.addAttribute("name", details.getDisplayName());
        model.addAttribute("myItems", items);
        model.addAttribute("pager", pager);

        return "/login/my_page";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto member) {
        memberService.signUp(member);
        return ResponseEntity.ok().build();
    }
}
