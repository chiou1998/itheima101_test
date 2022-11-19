package com.itheima.mm.dao;

import com.itheima.mm.pojo.WxMember;

public interface WxMemberDao {

    WxMember findNickName(String nickName);

    void addWxMember(WxMember wxMember);

    WxMember findById(Integer id);

    void update(WxMember wxMember);
}
