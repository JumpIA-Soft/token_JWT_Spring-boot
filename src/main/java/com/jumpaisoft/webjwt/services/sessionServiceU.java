package com.jumpaisoft.webjwt.services;

import java.util.Optional;

import com.jumpaisoft.webjwt.Repository.UserRepository;
import com.jumpaisoft.webjwt.entities.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class sessionServiceU {

    @Autowired
    UserRepository UserR;

    public boolean verificationUser(String username, String password) {
        // find in a DB the user with the username and password
        Optional<user> sessionFind = UserR.findByNameUser(username);
        // verify if the object isn´t null
        if (sessionFind.isPresent()) {
            user user = sessionFind.get();
            // verify if the password is correct
            if (user.getPasswordUser().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
