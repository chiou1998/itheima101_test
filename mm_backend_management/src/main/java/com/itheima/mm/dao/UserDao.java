package com.itheima.mm.dao;

import com.itheima.mm.pojo.User;

import java.util.List;

public interface UserDao {
    User findUserByUsername(String username);
}
