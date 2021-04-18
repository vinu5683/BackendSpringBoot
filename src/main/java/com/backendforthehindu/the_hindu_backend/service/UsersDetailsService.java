package com.backendforthehindu.the_hindu_backend.service;

import com.backendforthehindu.the_hindu_backend.entity.Users;
import com.backendforthehindu.the_hindu_backend.service.rest_services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersDetailsService implements UserDetailsService {

    UsersService usersService;

    @Autowired
    public UsersDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users u = usersService.findByName(username).iterator().next();

        if (username.equals(u.getEmail())) {
            return new User(u.getEmail(), u.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
