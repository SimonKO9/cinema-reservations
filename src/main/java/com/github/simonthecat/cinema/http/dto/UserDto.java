package com.github.simonthecat.cinema.http.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static java.util.stream.Collectors.*;

public class UserDto {

    private String username;
    private List<String> authorities;

    public UserDto(String username, List<String> authorities) {
        this.username = username;
        this.authorities = authorities;
    }


    public List<String> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return this.username;
    }

    public static UserDto fromUserDetails(UserDetails userDetails) {
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList());

        String username = userDetails.getUsername();
        return new UserDto(username, authorities);
    }

}
