package com.in28mins.rest.webservices.restfulwerbservices.dao;

import com.in28mins.rest.webservices.restfulwerbservices.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao {

    static List<User> userList = new ArrayList<User>();

    static {
        userList.add(new User(1, "Lionel Messi", new Date()));
        userList.add(new User(2, "Diego Maradona", new Date()));
        userList.add(new User(3, "Luis Figo", new Date()));
    }

    public List<User> getAllUser() {
        return userList;
    }

    public User getUser(int id) {
        User userTemp = null;
        for (User user : userList) {
            if (user.getId().equals(id))
                userTemp = user;
        }
        return userTemp;
    }

    public User addUser(User user){
        int id = 0;
        if(user.getId() == null)
            id = userList.size()+1;
        user.setId(id);
        userList.add(user);
        return user;
    }
}
