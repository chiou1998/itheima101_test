package com.itheima.mm.dao;

import com.itheima.mm.controller.QuestionController;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.QuestionItem;

import java.util.List;

public interface QuestionDao {

    Long findCountByCourseId(Integer id);

    Long findTotal(QueryPageBean queryPageBean);

    List<Question> findPageList(QueryPageBean queryPageBean);

    void addQuestion(Question question);

}
