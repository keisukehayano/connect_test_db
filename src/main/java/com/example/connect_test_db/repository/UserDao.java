package com.example.connect_test_db.repository;

import com.example.connect_test_db.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //@Autowired
    public UserDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 全件取得
    @Override
    public List<User> getUserAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("id, ");
        sb.append("name, ");
        sb.append("address, ");
        sb.append("phone, ");
        sb.append("update_date, ");
        sb.append("create_date, ");
        sb.append("delete_date ");
        sb.append("FROM ");
        sb.append("user");

        String sql = sb.toString();
        
        Map<String, String> param = new HashMap<>();

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);

        List<User> list = new ArrayList<User>();

        for (Map<String, Object> result : resultList) {
            User user = new User();
            user.setId((Long)result.get("id"));
            user.setName((String)result.get("name"));
            user.setAddress((String)result.get("address"));
            user.setPhone((String)result.get("phone"));
            user.setUpdateDate((Date)result.get("update_date"));
            user.setCreateDate((Date)result.get("create_date"));
            user.setDeleteDate((Date)result.get("deleet_date"));

            list.add(user);
        }
        return list;
    }


    // idを指定して取得
    public User getUserById(String id) {
        StringBuilder sb = new StringBuilder();
        User user = new User();
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        
        sb.append("SELECT ");
        sb.append("id, ");
        sb.append("name, ");
        sb.append("address, ");
        sb.append("phone, ");
        sb.append("update_date, ");
        sb.append("create_date, ");
        sb.append("delete_date ");
        sb.append("FROM ");
        sb.append("user ");
        sb.append("WHERE ");
        sb.append("id = :id");
        String sql = sb.toString();

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);

        for (Map<String, Object> result : resultList) {
            user.setId((Long)result.get("id"));
            user.setName((String)result.get("name"));
            user.setAddress((String)result.get("address"));
            user.setPhone((String)result.get("phone"));
            user.setUpdateDate((Date)result.get("update_date"));
            user.setCreateDate((Date)result.get("create_date"));
            user.setDeleteDate((Date)result.get("delete_date"));
        }

        return user;
    }

    // userを登録
    public int createUser(User user) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> param = new HashMap<>();
        param.put("name", user.getName());
        param.put("address", user.getAddress());
        param.put("phone", user.getPhone());
        String strNowdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(user.getUpdateDate());
        param.put("update_date", strNowdate);
        param.put("create_date", strNowdate);
        
        sb.append("INSERT INTO user( ");
        sb.append("name, ");
        sb.append("address, ");
        sb.append("phone, ");
        sb.append("update_date, ");
        sb.append("create_date ");
        sb.append(")VALUES( ");
        sb.append(":name, ");
        sb.append(":address, ");
        sb.append(":phone, ");
        sb.append(":create_date, ");
        sb.append(":update_date ");
        sb.append(" )");
        String sql = sb.toString();


        int result = jdbcTemplate.update(sql, param);


        return result;
    }

    // user削除
    public int deleteUserById(String id) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        sb.append("DELETE FROM user WHERE id = :id");
        String sql = sb.toString();
        int result = jdbcTemplate.update(sql, param);

        return result; 
    }

    // user論理削除
    public int logicalDeleteById(String id) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> params = new HashMap<>();
        Date nowDate = new Date();
        String strNowdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(nowDate);
        params.put("id", id);
        params.put("delete_date", strNowdate);

        sb.append("UPDATE user SET delete_date = :delete_date WHERE id = :id");
        String sql = sb.toString();

        int result = jdbcTemplate.update(sql, params);
        return result;
    }

    // user update
    public int updateUserById(User user) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> params = new HashMap<>();
        Date nowDate = new Date();
        String strNowDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(nowDate);

        sb.append("UPDATE user ");
        sb.append("SET name = :name, ");
        sb.append("address = :address, ");
        sb.append("phone = :phone, ");
        sb.append("update_date = :update_date ");
        sb.append("WHERE id = :id");
        String sql = sb.toString();

        params.put("name", user.getName());
        params.put("address", user.getAddress());
        params.put("phone", user.getPhone());
        params.put("update_date", strNowDate);
        params.put("id", user.getId().toString());

        int result = jdbcTemplate.update(sql, params);
        return result; 
    }
}
