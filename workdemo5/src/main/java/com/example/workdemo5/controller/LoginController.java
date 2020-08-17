package com.example.workdemo5.controller;

import com.example.workdemo5.entity.UserEntity;
import com.example.workdemo5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录判断
 * */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", consumes = "application/json")
    @ResponseBody
    public Map<String, Object> userLogin(@RequestBody UserEntity user, HttpServletRequest request){
        int type = user.getUserType();
        boolean result = userService.login(user);
        //创建session
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        if (result){
            //存信息到session中
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("password", user.getPassword());

            map.put("result","success");
            map.put("userType",type);
        }else {
            map.put("result","error");
        }
        return map;
    }
}
