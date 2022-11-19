package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.service.WxMemberService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {
    private QuestionService questionService = new QuestionService();
    private WxMemberService wxMemberService = new WxMemberService();

    @RequestMapping("/category/list")
    public void categoryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取请求参数
            String authorization = request.getHeader("Authorization");
            Integer id = Integer.valueOf(authorization.substring(7));
            //获取请求中的请求参数，封装到map中
            Map parameterMap = JsonUtils.parseJSON2Object(request, Map.class);
            WxMember wxMember = wxMemberService.findById(id);
            parameterMap.put("wxMember", wxMember);
            List<Map> categoryList = questionService.categoryList(parameterMap);
            JsonUtils.printResult(response, new Result(true, "获取题目分类列表成功", categoryList));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response, new Result(false, "获取题目分类列表失败"));
        }
    }

    @RequestMapping("/question/list")
    public void questionList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取请求参数
            String authorization = request.getHeader("Authorization");
            Integer id = Integer.valueOf(authorization.substring(7));
            //获取请求中的请求参数，封装到map中
            Map parameterMap = JsonUtils.parseJSON2Object(request, Map.class);
            WxMember wxMember = wxMemberService.findById(id);
            parameterMap.put("wxMember", wxMember);
            Map resultMap = questionService.questionList(parameterMap);
            JsonUtils.printResult(response, new Result(true, "查询题目列表成功", resultMap));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response, new Result(false, "查询题目列表失败"));
        }
    }
}