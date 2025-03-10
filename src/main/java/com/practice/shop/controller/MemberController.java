package com.practice.shop.controller;

import com.practice.shop.domain.MyUserDetails;
import com.practice.shop.dto.member.SignUpRequestDto;
import com.practice.shop.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public String myPage(Authentication auth, Model model) {
        MyUserDetails details = (MyUserDetails) auth.getPrincipal();
        model.addAttribute("name", details.getDisplayName());
        model.addAttribute("myItems", memberService.myPage(auth.getName()));
        return "/login/my_page";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto member) {
        memberService.signUp(member);
        return ResponseEntity.ok().build();
    }

}
