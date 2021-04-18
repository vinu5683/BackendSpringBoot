package com.backendforthehindu.the_hindu_backend.service.rest_services;

import com.backendforthehindu.the_hindu_backend.dao.UsersDAO;
import com.backendforthehindu.the_hindu_backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersDAO usersDAO;

    @Override
    @Transactional
    public Iterable<Users> findByName(String username) {
        return usersDAO.findByName(username);
    }

    @Override
    @Transactional
    public void addUser(Users u) {
        usersDAO.save(u);
    }

    @Override
    @Transactional
    public boolean isUserPresent(String email, String password) {
        return usersDAO.findUserByEmailPassword(email, password) >= 1;
    }

    @Override
    @Transactional
    public void updateNow(Users u) {
        try {
            usersDAO.save(u);
            System.out.println("Updated");
        } catch (Exception e) {
            System.out.println("Not updated");
        }
    }

}
