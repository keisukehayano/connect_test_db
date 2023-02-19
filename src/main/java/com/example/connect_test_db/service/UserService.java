package com.example.connect_test_db.service;

import com.example.connect_test_db.repository.IUserDao;
import com.example.connect_test_db.entity.User;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final IUserDao dao;

    //@Autowired
    public UserService(IUserDao dao) {
        this.dao = dao;
    }

    public List<User> getUserAll() {
        return dao.getUserAll();
    }

    public User getUserById(String id) {
        return dao.getUserById(id);
    }
    
    public int createUser(User user) {
        return dao.createUser(user);
    }

    public int deleteUserById(String id) {
        return dao.deleteUserById(id);
    }

    public int logicalDeleteById(String id) {
        return dao.logicalDeleteById(id);
    }

    public int updateUserById(User user) {
        return dao.updateUserById(user);
    }
}
