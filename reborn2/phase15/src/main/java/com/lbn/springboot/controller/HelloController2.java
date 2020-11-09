package com.lbn.springboot.controller;


import com.lbn.springboot.config.Demo1CP;
import com.lbn.springboot.mapper.UserMapper;
import com.lbn.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@EnableConfigurationProperties(Demo1CP.class)
public class HelloController2 {

    @Value("${leyou.name}")
    private String leyouname;

    @Autowired
    private Demo1CP Demo1CP;

    @Autowired
    private UserMapper userMapper;



    @RequestMapping("hello")
    public ResponseEntity<String> hello(){

        return ResponseEntity.ok("你好阿哈哈哈哈1");
    }

    @RequestMapping("hello2")
    public ResponseEntity<Object> hello2(){
        List<User> list =new ArrayList();
        User user = new User();
        user.setName("张三2222222");
        User user2 = new User();
        user2.setName("李四111");

        list.add(user);
        list.add(user2);
        return ResponseEntity.ok(list);
    }

    @RequestMapping("config")
    public ResponseEntity<Object> config(){
        return ResponseEntity.ok(leyouname);
    }

    @RequestMapping("config2")
    public ResponseEntity<Object> config2(){
        return ResponseEntity.ok(Demo1CP.getAge());
    }

    @RequestMapping("mybatis")
    public ResponseEntity<Object> mybatis(){
        List<User> users = userMapper.queryUserList();
        return ResponseEntity.ok(users);
    }
}
