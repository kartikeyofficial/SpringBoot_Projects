package com.example.xmlConfiguration;

import java.util.List;

public class UserService {
    public List<String> userNames;

    public UserService(List<String> userNames){
        this.userNames = userNames;
    }

    public void getUserNames(){
        System.out.println(userNames);
    }
}
