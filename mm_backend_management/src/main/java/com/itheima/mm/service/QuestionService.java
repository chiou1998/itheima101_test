package com.itheima.mm.service;

import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-09-26  15:17
 */
public interface QuestionService {
    PageResult findByPage(QueryPageBean queryPageBean) throws Exception;

    void addQuestion(Question question) throws Exception;
}
