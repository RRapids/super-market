package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Member;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

public class MemberDAOTest {
    private MemberDAO memberDAO = DAOFactory.getMamberDAOInstance();

    @Test
    public void insertMember() throws SQLException{
        Member member = new Member();
        member.setName("假人");
        member.setPhone("15161155167");
        member.setAddress("中华台北");
        System.out.println(memberDAO.insertMember(member));
    }

}