package com.github.simonthecat.cinema.http.rs;

import com.github.simonthecat.cinema.http.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserResource {

    @Autowired
    UserDetailsManager userDetailsService;

    @RequestMapping(value = "/api/users/{username}")
    public UserDto getUser(@PathVariable String username, Principal principal) {
        if(username.equals(principal.getName())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return UserDto.fromUserDetails(userDetails);
        } else {
            throw new SecurityException("You may only see your own profile");
        }
    }
}
