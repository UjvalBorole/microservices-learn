package com.lcwd.user.service.Service;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get a user
    User getUser(String uId);
}
