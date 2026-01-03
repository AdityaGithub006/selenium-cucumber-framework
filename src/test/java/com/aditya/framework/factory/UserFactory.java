package com.aditya.framework.factory;

import com.aditya.framework.data.TestDataReader;
import com.aditya.framework.model.User;

public class UserFactory {

    public User getUser(String role) {
        String prefix = role.toLowerCase();

        String username = TestDataReader.get(prefix + ".username");
        String password = TestDataReader.get(prefix + ".password");

        return new User(role, username, password);
    }
}