package com.itheima.mm.controller;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-09-13  09:58
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody User parameterUser, HttpSession session) throws IOException {
        try {
            //2. 调用业务层的方法校验登录
            User loginUser = userService.login(parameterUser);

            //很关键的事情，登录成功之后，一定要保存登录状态，存到session中
            session.setAttribute(Constants.USER_SESSION_KEY,loginUser);

            return new Result(true,"登录成功",loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }
    }
    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //退出登录就是清除session中保存的用户信息
        request.getSession().invalidate();
        return new Result(true,"退出登录成功");
    }
}
