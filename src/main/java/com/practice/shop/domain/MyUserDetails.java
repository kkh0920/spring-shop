package com.practice.shop.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MyUserDetails extends User {

    private final String displayName;

    public MyUserDetails(String displayName, String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.displayName = displayName;
    }
}