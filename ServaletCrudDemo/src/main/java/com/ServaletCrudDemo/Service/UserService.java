package com.ServaletCrudDemo.Service;

import com.ServaletCrudDemo.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<Integer, User> userDB;

    public UserService() {
         userDB = new HashMap<>();
    }
}
