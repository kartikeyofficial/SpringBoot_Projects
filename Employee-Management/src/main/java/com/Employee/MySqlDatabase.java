package com.Employee;

import org.springframework.stereotype.Component;

@Component
public class MySqlDatabase implements DataBase {

    @Override
    public void save(String user) {
        System.out.println("MySql database is Saving");

    }
}
