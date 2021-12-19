package com.example.NotCursedWork.services;

import com.example.NotCursedWork.dao.UserDAO;
import com.example.NotCursedWork.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser(String username) {
        return userDAO.findByUsername(username);
    }

    public void save(User user) {
        userDAO.save(user);
    }
}