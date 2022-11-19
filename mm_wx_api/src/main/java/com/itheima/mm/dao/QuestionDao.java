package com.itheima.mm.dao;

import com.itheima.mm.pojo.Question;

import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.dao
 *
 * @author Leevi
 * 日期2020-09-18  09:38
 */
public interface QuestionDao {
    List<Map> findCategoryListByCatalog(Map parameterMap);
    List<Map> findCategoryListByCompany(Map parameterMap);

    Map findCategoryDetail(Map parameterMap);

    List<Question> findQuestionListByCategory(Map parameterMap);
}
