package com.itheima.mm.service;

import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-09-26  15:17
 */
public interface CourseService {
    void addCourse(Course course) throws Exception;

    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    void updateCourse(Course course) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<Course> findAll(Map parameterMap) throws Exception;
}
