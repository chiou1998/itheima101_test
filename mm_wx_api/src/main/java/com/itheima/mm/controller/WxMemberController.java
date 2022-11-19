package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.service.WxMemberService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WxMemberController {
    private WxMemberService wxMemberService = new WxMemberService();
    @RequestMapping("/wxMember/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取请求参数封装到WxMember中
            WxMember wxMember = JsonUtils.parseJSON2Object(request,WxMember.class);
            //判断当前用户是否已经注册过：根据nickname到t_wx_member表中查询
            WxMember loginMember = wxMemberService.findByNickname(wxMember.getNickName());
            Map resultMap = new HashMap<>();
            if (loginMember == null) {
                wxMember.setCreateTime(DateUtils.parseDate2String(new Date()));
                wxMemberService.addWxMember(wxMember);
                resultMap.put("token",wxMember.getId());
                resultMap.put("userInfo",wxMember);
            }else {
                resultMap.put("token",loginMember.getId());
                resultMap.put("userInfo",loginMember);
            }
            JsonUtils.printResult(response,new Result(true,"登录成功",resultMap));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"登录失败"));
        }
    }
    @RequestMapping("/wxMember/setCityCourse")
    public void setCityCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //将客户端携带过来的Authorization(就是该用户的标识)
            String authorization = request.getHeader("Authorization");
            Integer id = Integer.valueOf(authorization.substring(7));
            //获取请求参数
            Map parameterMap = JsonUtils.parseJSON2Object(request, Map.class);
            WxMember wxMember = wxMemberService.findById(id);
            if (wxMember != null) {
                wxMember.setCityId((Integer) parameterMap.get("cityID"));
                wxMember.setCourseId((Integer) parameterMap.get("subjectID"));
            }
            wxMemberService.update(wxMember);
            JsonUtils.printResult(response,new Result(true,"成功"));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"设置城市学科失败"));

        }
    }
}
