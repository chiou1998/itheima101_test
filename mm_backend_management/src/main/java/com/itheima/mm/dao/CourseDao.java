package com.itheima.mm.dao;

import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    void addCourse(Course course);

    Long findTotal(QueryPageBean queryPageBean);

    List<Course> findPageList(QueryPageBean queryPageBean);

    void updateCourse(Course course);

    void deleteById(Integer id);

    List<Course> findAll(Map parameterMap);
}
