package com.soft1841.cn.service;

import com.soft1841.cn.entity.Member;

import java.util.List;

/**
 * 会员类别的业务逻辑接口
 * @author Yue Tang
 */
public interface MemberService {

    /**
     * 添加会员
     * @param member
     */
    Long insertMember(Member member);

    /**
     * 删除会员
     * @param id
     */
    void deleteMemberById(long id);

    /**
     * 查询所有会员
     * @return
     */
    List<Member> selectAllMember();

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    Member getMemberById(long id);
}
