package com.lbn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbn.springboot.Demo1Application;
import com.lbn.springboot.mapper.UserMapper;
import com.lbn.springboot.pojo.User;
import com.lbn.springboot.repository.UserRepository;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Demo1Application.class)
public class Demo1Test {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test1() {
        List<User> users = userMapper.queryUserList();
        users.forEach(u-> System.out.println(u));
    }

    @Test
    public void test2() {
        List<User> users = userRepository.findAll();
        users.forEach(u-> System.out.println(u));
    }

    @Test
    public void test3() throws IOException {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps("user.findAll");

        List<User> users;
        ObjectMapper objectMapper=new ObjectMapper();
        if(ops.get()==null){
            users = userRepository.findAll();


            String json = objectMapper.writeValueAsString(users);

            ops.set(json);
            System.out.println("从数据库查找数据");
        }else {
            String s = ops.get();
             users = objectMapper.readValue(s,objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            System.out.println("从redis查找数据");
        }


        users.forEach(u-> System.out.println(u));
    }
}
