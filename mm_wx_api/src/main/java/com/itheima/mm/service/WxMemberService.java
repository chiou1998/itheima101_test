package com.itheima.mm.service;

import com.itheima.mm.dao.WxMemberDao;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class WxMemberService {
    public WxMember findByNickname(String nickName) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
        WxMember wxMember = memberDao.findNickName(nickName);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return wxMember;
    }

    public void addWxMember(WxMember wxMember) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
         memberDao.addWxMember(wxMember);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    public WxMember findById(Integer id) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
        WxMember wxMember = memberDao.findById(id);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return wxMember;
    }

    public void update(WxMember wxMember) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
        memberDao.update(wxMember);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }
}
