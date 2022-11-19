package com.itheima.mm.dao;

import com.itheima.mm.pojo.QuestionItem;

import java.util.List;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-09-18  15:01
 */
public interface QuestionItemDao {
    List<QuestionItem> findQuestionItemList(Integer questionId);
}
