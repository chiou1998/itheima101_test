package com.itheima.mm.service;

import com.itheima.mm.pojo.User;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-09-26  15:17
 */
public interface UserService {

    User login(User parameterUser) throws Exception;
}
