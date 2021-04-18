package com.backendforthehindu.the_hindu_backend.service.rest_services;

import com.backendforthehindu.the_hindu_backend.entity.Users;

public interface UsersService {
    Iterable<Users> findByName(String username);

    void addUser(Users u);

    boolean isUserPresent(String email,String password);

    void updateNow(Users u);
}
