package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;
import java.util.Map;

public interface TagDao {
    Long findCountByCourseId(Integer id);
    List<Tag> findTagListByCourseId(Integer courseId);

    void associationQuestionTag(Map parameterMap);
}
