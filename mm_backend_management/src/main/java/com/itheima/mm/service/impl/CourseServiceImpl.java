package com.itheima.mm.service.impl;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.TagDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-09-13  11:06
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CatalogDao catalogDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private QuestionDao questionDao;
    @Override
    public void addCourse(Course course) throws Exception {
        //调用Dao层的方法执行添加数据的SQL语句
        courseDao.addCourse(course);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        //1. 查询总条数
        Long total = courseDao.findTotal(queryPageBean);

        //2. 查询当前页数据集合
        List<Course> courseList = courseDao.findPageList(queryPageBean);
        //3. 将上述查询到的俩数据封装到PageResult中，并返回
        return new PageResult(total,courseList);
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        courseDao.updateCourse(course);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //判断该学科有没有关联的二级目录
        Long catalogCount = catalogDao.findCountByCourseId(id);
        if (catalogCount > 0) {
            //说明该学科有关联的二级目录
            throw new RuntimeException("该学科有相关联的二级目录，不能删除");
        }

        //判断该学科有没有关联的标签
        Long tagCount = tagDao.findCountByCourseId(id);
        if (tagCount > 0) {
            //说明该学科有关联的标签
            throw new RuntimeException("该学科有相关联的标签，不能删除");
        }

        //判断该学科有没有关联的题目
        Long questionCount = questionDao.findCountByCourseId(id);
        if (questionCount > 0) {
            //说明该学科有关联的题目
            throw new RuntimeException("该学科有相关联的题目，不能删除");
        }

        courseDao.deleteById(id);
    }

    @Override
    public List<Course> findAll(Map parameterMap) throws Exception {
        //调用dao层的方法，查询所有学科信息
        List<Course> courseList = courseDao.findAll(parameterMap);

        return courseList;
    }
}
