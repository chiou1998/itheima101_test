package com.itheima.mm.service.impl;

import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.QuestionItemDao;
import com.itheima.mm.dao.TagDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.QuestionItem;
import com.itheima.mm.pojo.Tag;
import com.itheima.mm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.mm.service
 *
 * @author Leevi
 * 日期2020-09-15  09:10
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionItemDao questionItemDao;
    @Autowired
    private  TagDao tagDao;
    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        //1. 查询总条数
        Long total = questionDao.findTotal(queryPageBean);

        //2. 查询当前页数据集合
        List<Question> questionList = questionDao.findPageList(queryPageBean);
        return new PageResult(total,questionList);
    }

    @Transactional
    @Override
    public void addQuestion(Question question) throws Exception {
        try {
            //保存题目信息:
            //1. 将题目自身的信息，保存到t_question表
            questionDao.addQuestion(question);
            //2. 将题目选项信息，保存到t_question_item表中
            List<QuestionItem> questionItemList = question.getQuestionItemList();
            /*if (questionItemList != null && questionItemList.size() > 0) {
                //说明该题有选项,才保存选项信息
                QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
                for (QuestionItem questionItem : questionItemList) {
                    //遍历出每一个选项
                    //手动设置每一个选项的questionId的值
                    questionItem.setQuestionId(question.getId());
                    //调用QuestionItemDao的方法，保存题目的选项信息
                    questionItemDao.addQuestionItem(questionItem);
                }
            }*/

            //批量添加
            if (questionItemList != null && questionItemList.size() > 0) {
                for (QuestionItem questionItem : questionItemList) {
                    questionItem.setQuestionId(question.getId());
                }
                questionItemDao.addQuestionItemList(questionItemList);
            }
            //3. 绑定题目的标签信息: 往tr_question_tag表中添加数据
            List<Tag> tagList = question.getTagList();
            if (tagList != null && tagList.size() > 0) {
                for (Tag tag : tagList) {
                    Map parameterMap = new HashMap();
                    parameterMap.put("questionId",question.getId());
                    parameterMap.put("tagId",tag.getId());
                    tagDao.associationQuestionTag(parameterMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
