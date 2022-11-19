package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CityService;
import com.itheima.mm.service.CourseService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {
    private CityService cityService = new CityService();
    private CourseService CourseService = new CourseService();

    @RequestMapping("/common/citys")
    public void cities(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map parameterMap = JsonUtils.parseJSON2Object(request,Map.class);
            //调用业务层处理请求获取数据
            Map resultMap = cityService.findCityList(parameterMap);
            JsonUtils.printResult(response,new Result(true,"获取成功",resultMap));
        } catch (Exception e) {
          e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取失败"));

        }
    }
    @RequestMapping("/common/courseList")
    public void courseList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Course> courseList = CourseService.findAllCourseList();
            JsonUtils.printResult(response,new Result(true,"获取学科列表成功",courseList));

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取学科列表成功"));
        }
    }
}
