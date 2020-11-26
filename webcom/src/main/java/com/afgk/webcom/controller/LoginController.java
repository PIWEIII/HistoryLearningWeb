package com.afgk.webcom.controller;

import com.afgk.webcom.bean.User;
import com.afgk.webcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Piweiii
 * @Date: 2020/11/11/16:08
 * @Description:登录控制器
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 跳转
     * login page
     * @return
     */
    @GetMapping("/loginPage")
    public String toLoginPage(){
        return "index";
    }

    @GetMapping("/login")
    @ResponseBody
    public LoginCheck login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Map<String,String> map){
        if(username.equals("") || password.equals("")||username==null||password==null){
            map.put("msg","用户名或密码未填写");
            return new LoginCheck(username,0,"用户名或密码未填写");
        }
        User user = userService.findUserByUN(username);
        if(user == null){
            map.put("msg","用户不存在");
            return new LoginCheck(username,0,"用户不存在");
        }
        if(!user.getPassword().equals(password)){
            map.put("msg","密码错误");
            //System.out.println("密码错误");
            return new LoginCheck(username,1,"密码错误");
        }
        map.put("msg","登录成功");
        //System.out.println("登录成功"+"用户名："+username+","+"密码："+password);
        return new LoginCheck(username,2,"登录成功");
    }
}

class LoginCheck{
    private String username;
    private int code;
    private String explaination;

    public LoginCheck(String username, int code,String explaination) {
        this.username = username;
        this.code = code;
        this.explaination = explaination;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }
}