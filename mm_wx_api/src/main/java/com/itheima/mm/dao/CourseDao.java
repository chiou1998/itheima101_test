package com.itheima.mm.dao;

import com.itheima.mm.pojo.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findCourseList();
}
