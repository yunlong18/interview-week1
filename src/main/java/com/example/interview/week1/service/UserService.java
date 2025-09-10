package com.example.interview.week1.service;

import com.example.interview.week1.entity.User;

public interface UserService {

    User create(User user);

    User findById(String id);

    User findByUsername(String username);
}
