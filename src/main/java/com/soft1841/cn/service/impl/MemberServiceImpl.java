package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.MemberDAO;
import com.soft1841.cn.entity.Member;
import com.soft1841.cn.service.MemberService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO = DAOFactory.getMemberDAOInstance();

    @Override
    public Long insertMember(Member member) {
        long result = 0;
        try {
            result = memberDAO.insertMember(member);
        } catch (SQLException e) {
            System.err.println("新增会员异常");
        }
        return result;
    }

    @Override
    public void deleteMemberById(long id) {
        try {
            memberDAO.deleteMemberById(id);
        } catch (SQLException e){
            System.err.println("删除会员异常");
        }
    }

    @Override
    public List<Member> selectAllMember() {
        List<Member> memberList = new ArrayList<>();
        try {
            memberList = memberDAO.selectAllMember();
        } catch (SQLException e) {
            System.err.println("查询所有会员异常");
        }
        return memberList;
    }

    @Override
    public Member getMemberById(long id) {
        Member member = new Member();
        try {
            member = memberDAO.getMemberById((int) id);
        } catch (SQLException e) {
            System.err.println("查询单个会员异常");
        }
        return member;
    }
}
