package com.itheima.mm.service.impl;

import com.itheima.mm.dao.UserDao;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-09-13  10:02
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 校验登录的方法
     * @param parameterUser  参数中包含username和password
     * @return 返回值是查询到的user
     */
    @Override
    public User login(User parameterUser) throws Exception {
        //1. 根据用户名查找用户
        User loginUser = userDao.findUserByUsername(parameterUser.getUsername());
        //2. 判断loginUser是否为空，如果为空，表示用户名错误
        if (loginUser != null) {
            //用户名正确
            //判断密码是否正确
            if (loginUser.getPassword().equals(parameterUser.getPassword())) {
                //密码正确，登录成功
                return loginUser;
            }else {
                //密码错误

                throw new RuntimeException("密码错误");
            }
        }else {
            //用户名错误

            throw new RuntimeException("用户名错误");
        }
    }
}
