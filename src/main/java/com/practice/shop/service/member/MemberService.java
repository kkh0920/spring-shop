package com.practice.shop.service.member;

import com.practice.shop.domain.Item;
import com.practice.shop.domain.Member;
import com.practice.shop.dto.item.ItemResponseDto;
import com.practice.shop.dto.member.SignUpRequestDto;
import com.practice.shop.exception.item.PageNotFoundException;
import com.practice.shop.exception.member.*;
import com.practice.shop.repository.ItemRepository;
import com.practice.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

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

    public Page<ItemResponseDto> myPage(Integer page, Integer pageSize, String username) {
        if(page < 0) {
            throw new PageNotFoundException();
        }
        Optional<Member> member = memberRepository.findByUsername(username);
        if(member.isEmpty()) {
            throw new UserNotFoundException();
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("insertDate").descending());
        Page<ItemResponseDto> dto = itemRepository.findAllByMember(member.get(), pageable).map(ItemResponseDto::from);
        if (dto.getTotalPages() < page) {
            throw new PageNotFoundException();
        }
        return dto;
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
