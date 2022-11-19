package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> findTagListByQuestionId(Integer questionId);
}
