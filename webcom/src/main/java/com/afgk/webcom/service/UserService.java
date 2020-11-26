package com.afgk.webcom.service;

import com.afgk.webcom.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Piweiii
 * @Date: 2020/11/10/17:02
 * @Description:
 */
@Repository
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查找
     * 返回所有用户
     * @return
     */
    public List<User> findAllUsers(){
        Query query = new Query();
        mongoTemplate.find(new org.springframework.data.mongodb.core.query.Query(new Criteria()),User.class);
        List<User> users = mongoTemplate.find(query,User.class);
        return users;
    }

    /**
     * 查找：
     * 根据用户名/昵称查找
     * @param username
     * @return
     */
    public User findUserByUN(String username){
        Query query = new Query(Criteria.where("username").is(username));
        User user = mongoTemplate.findOne(query,User.class);
        return user;
    }

    /**
     * 添加：
     * 直接存储前端返回的用户对象
     * @param user
     */
    public void addUser(User user){
        mongoTemplate.save(user);
    }
}
