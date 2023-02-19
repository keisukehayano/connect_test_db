package com.example.connect_test_db.repository;

import com.example.connect_test_db.entity.User;

import java.util.List;

/**
 * インターフェース
 */
public interface IUserDao {

    /**
     * 全件取得
     * @return
     */
    List<User> getUserAll();

    /**
     * idを指定して取得
     * @return
     */
    User getUserById(String id);

    /**
     * user登録
     * @param user
     * @return
     */
    int createUser(User user);

    /**
     * user削除
     * @param id
     */
    int deleteUserById(String id);

    /**
     * user論理削除
     * @param id
     * @return
     */
    int logicalDeleteById(String id);

    /**
     * 
     * @param user
     * @return
     */
    int updateUserById(User user);
} 
