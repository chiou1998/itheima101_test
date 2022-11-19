package com.itheima.mm.service;

import com.itheima.mm.constants.QuestionConst;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class QuestionService {
    public List<Map> categoryList(Map parameterMap) throws Exception {
        //parameterMap中有什么数据：categoryType、categoryKind、wxMember:id、学科id、城市id
        //我们将业务逻辑判断放在业务层
        //1. 判断到底是: 刷题、错题本、我的练习、收藏题目
        Integer categoryType = null;
        if (parameterMap.get("categoryType") instanceof String) {
            categoryType =  Integer.valueOf((String)parameterMap.get("categoryType"));
        }else {
            categoryType = (Integer) parameterMap.get("categoryType");
        }

        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);

        List<Map> categoryList = null;
        if (categoryType == QuestionConst.CategoryType.EXERCISE.getCode()) {
            //刷题
            //再判断: categoryKind的值
            Integer categoryKind = null;
            if (parameterMap.get("categoryKind") instanceof String) {
                categoryKind = Integer.valueOf((String)parameterMap.get("categoryKind"));
            }else {
                categoryKind = (Integer) parameterMap.get("categoryKind");
            }


            if (categoryKind == QuestionConst.CategoryKind.CATALOG.getCode()) {
                //按照技术点(学科的二级目录)
                categoryList = questionDao.findCategoryListByCatalog(parameterMap);
            }else if(categoryKind == QuestionConst.CategoryKind.COMPANY.getCode()){
                //按照企业查找
                categoryList = questionDao.findCategoryListByCompany(parameterMap);
            }
        }


        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return categoryList;
    }

    public Map questionList(Map parameterMap) throws Exception {
        Integer categoryType = null;
        if (parameterMap.get("categoryType") instanceof String) {
            categoryType = Integer.valueOf((String) parameterMap.get("categoryType"));
        } else {
            categoryType = (Integer) parameterMap.get("categoryType");
        }
        Map resultMap = null;


        if (categoryType == QuestionConst.CategoryType.EXERCISE.getCode()) {
            //1.查询当前分类的详情
            SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);

            resultMap = questionDao.findCategoryDetail(parameterMap);

            //2. 查询当前分类下的题目列表信息:
            //判断当前是刷题？错题集？我的练习？收藏题目？

            List<Question> questionList = questionDao.findQuestionListByCategory(parameterMap);
            resultMap.put("items", questionList);
            SqlSessionFactoryUtils.commitAndClose(sqlSession);
        }
        return resultMap;
    }
}
