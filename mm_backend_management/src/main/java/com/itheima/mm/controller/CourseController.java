package com.itheima.mm.controller;

import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.CourseService;
import com.itheima.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-09-13  11:01
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/add")
    public Result addCourse(@RequestBody Course course, HttpSession session) throws IOException {
        try {
            //2. 自己设置学科信息: createDate、userId、orderNo
            course.setCreateDate(DateUtils.parseDate2String(new Date()));
            //获取登录的用户信息
            User user = (User) session.getAttribute(Constants.USER_SESSION_KEY);
            course.setUserId(user.getId());
            course.setOrderNo(1);
            //3. 调用业务层的方法，保存学科信息
            courseService.addCourse(course);

            //成功
            return new Result(true,"添加学科成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加学科失败");
        }
    }

    @RequestMapping("/page")
    public Result queryPage(@RequestBody QueryPageBean queryPageBean) throws IOException {
        try {
            //2. 调用业务层的方法，进行分页查询
            PageResult pageResult = courseService.findPage(queryPageBean);
            //查询成功
            return new Result(true,"分页查询学科列表成功",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"分页查询学科列表失败");
        }
    }

    @RequestMapping("/update")
    public Result updateCourse(@RequestBody Course course) throws IOException {
        try {
            //2. 调用业务层的方法修改学科信息
            courseService.updateCourse(course);
            //修改成功
            return new Result(true,"修改学科成功");
        } catch (Exception e) {
            e.printStackTrace();
            //修改失败
            return new Result(false,"修改学科失败");
        }
    }

    @RequestMapping("/delete")
    public Result deleteCourse(int id) throws IOException {
        try {
            //2. 调用业务层的方法根据id删除学科
            courseService.deleteById(id);
            //删除成功
            return new Result(true,"删除学科成功");
        } catch (Exception e) {
            e.printStackTrace();
            //删除失败
            return new Result(false,e.getMessage());
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(@RequestParam Map parameterMap) throws IOException {
        try {
            //调用业务层的方法，加载所有学科信息
            List<Course> courseList = courseService.findAll(parameterMap);
            //没有异常，则查询成功
            return new Result(true,"加载所有学科成功",courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"加载所有学科失败");
        }
    }
}
