package com.lbn.springboot.mapper;

import com.lbn.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> queryUserList();
}
