package com.practice.shop.controller;

import com.practice.shop.dto.member.SignUpRequestDto;
import com.practice.shop.exception.member.UserNotAuthenticatedException;
import com.practice.shop.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "/login/login";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "/login/sign_up";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        if(auth == null || !auth.isAuthenticated()) {
            throw new UserNotAuthenticatedException();
        }
        return "/login/my_page";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto member) {
        memberService.signUp(member);
        return ResponseEntity.ok().build();
    }

}
