package com.afgk.webcom.controller;

import com.afgk.webcom.bean.User;
import com.afgk.webcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Piweiii
 * @Date: 2020/11/10/17:01
 * @Description:用户控制器
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     *查找所有用户
     */
    @GetMapping("/user")
    public List<User> listUsers(){
        List<User> users = userService.findAllUsers();
        return users;
    }

    /**
     * 注册功能
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/signUp")
    public Map<String,String> signUp(@RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam String nickname){
        Map<String,String> map = new HashMap<>();
        if(username.equals("") || password.equals("")||username == null||password==null){
            map.put("msg","用户名或密码为空");
            return map;
        }
        if(userService.findUserByUN(username) != null){
            map.put("msg","用户名已经存在，请重试");
            return map;
        }
        if(userService.findUserByUN(nickname) != null){
            map.put("msg","昵称已经存在，请重试");
            return map;
        }
        userService.addUser(new User(username,password,nickname));
        map.put("msg","注册成功");
        return map;
    }
}
