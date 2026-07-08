package com.Employee;

import org.springframework.stereotype.Service;

@Service
public class userService {
    DataBase dataBase;
    public userService(DataBase dataBase){
        this.dataBase = dataBase;
    }
    public void saveUser(String user){
        System.out.println("User Services save User");
        dataBase.save(user);
    }
}
