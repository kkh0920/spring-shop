package com.practice.shop.service.member;

import com.practice.shop.domain.Member;
import com.practice.shop.dto.member.SignUpRequestDto;
import com.practice.shop.exception.member.DisplayNameBlankException;
import com.practice.shop.exception.member.IdBlankException;
import com.practice.shop.exception.member.IdDuplicateException;
import com.practice.shop.exception.member.PasswordBlankException;
import com.practice.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public void signUp(SignUpRequestDto signUpRequestDto) {
        Member member = new Member();
        member.setUsername(signUpRequestDto.getUsername());
        member.setPassword(signUpRequestDto.getPassword());
        member.setDisplayName(signUpRequestDto.getDisplayName());
        validation(member);

        member.setPassword(encoder.encode(signUpRequestDto.getPassword()));
        memberRepository.save(member);
    }

    private void validation(Member member) {
        String username = member.getUsername();
        String password = member.getPassword();
        String displayName = member.getDisplayName();

        if (username == null || username.isEmpty()) {
            throw new IdBlankException();
        } else if(memberRepository.findByUsername(username).isPresent()) {
            throw new IdDuplicateException();
        } else if (password == null || password.isEmpty()) {
            throw new PasswordBlankException();
        } else if (displayName == null || displayName.isEmpty()) {
            throw new DisplayNameBlankException();
        }
    }
}
