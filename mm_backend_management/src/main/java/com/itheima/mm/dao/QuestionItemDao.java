package com.itheima.mm.dao;

import com.itheima.mm.pojo.QuestionItem;

import java.util.List;

public interface QuestionItemDao {
/*    void addQuestionList(List<QuestionItem> questionItemList);
    void addQuestionItem(QuestionItem questionItem);*/
    void addQuestionItem(QuestionItem questionItem);
    void addQuestionItemList(List<QuestionItem> questionItemList);
}
