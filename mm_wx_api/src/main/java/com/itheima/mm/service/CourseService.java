package com.itheima.mm.service;

import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class CourseService {
    public List<Course> findAllCourseList() throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        List<Course> courseList = courseDao.findCourseList();
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return courseList;
    }
}
