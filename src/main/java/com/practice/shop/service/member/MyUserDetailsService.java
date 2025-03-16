package com.practice.shop.service.member;

import com.practice.shop.domain.Member;
import com.practice.shop.dto.MyUserDetails;
import com.practice.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUsername(username);
        if(member.isEmpty()) {
            throw new UsernameNotFoundException("해당 아이디는 존재하지 않습니다.");
        }
        Member entity = member.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("일반 유저"));
        return new MyUserDetails(entity.getDisplayName(), entity.getUsername(), entity.getPassword(), authorities);
    }
}
