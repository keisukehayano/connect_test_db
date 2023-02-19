package com.example.connect_test_db.controller;

import com.example.connect_test_db.service.UserService;
import com.example.connect_test_db.entity.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserController {

    private final UserService userService;

    //@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * 全件取得
     * @return
     */
    @GetMapping("/users")
    public String getUserALL() {
        List<User> list = new ArrayList<User>();

        list = userService.getUserAll();

        return list.toString();
    } 

    /**
     * idを指定して取得
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") String id) {
        User user = new User();

        user = userService.getUserById(id);

        return user.toString();
    }

    /**
     * userを登録
     * @param name
     * @param address
     * @param phone
     * @return
     */
    @PostMapping("/user")
    public int createUser(
        @RequestParam("name") String name,
        @RequestParam("address") String address,
        @RequestParam("phone") String phone) {

        Date nowDate = new Date();
        User user = new User();

        user.setName(name);
        user.setAddress(address);
        user.setPhone(phone);
        user.setCreateDate(nowDate);
        user.setUpdateDate(nowDate);

        int result = userService.createUser(user);

        return result;
    }

    /**
     * 物理削除
     * @param id
     * @return
     */
    @DeleteMapping("/user")
    public int deleteUserById(@RequestParam("id") String id) {
        int result = userService.deleteUserById(id);

        return result;
    }

    /**
     * 論理削除
     * @param id
     * @return
     */
    @DeleteMapping("/l_user")
    public int logicalDeleteById(@RequestParam("id") String id) {
        int result = userService.logicalDeleteById(id);

        return result;
    }

    
    @PutMapping("/user")
    public int updateUserById(
        @RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("address") String address,
        @RequestParam("phone") String phone) {

        Date now = new Date();
        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        user.setPhone(phone);
        user.setUpdateDate(now);

        int result = userService.updateUserById(user);
        return result;
    }
}
