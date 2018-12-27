package com.soft1841.cn.service;

import com.soft1841.cn.entity.Member;
import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;

import java.util.List;

/**
 * 会员服务接口测试类
 *
 * @author 腾飞
 * 2018.12.26
 */
public class MemberServiceTest {
    private MemberService memberService = ServiceFactory.getMemberServiceInstance();

    @Test
    public void insertMember() {
        Member member = new Member();
        member.setName("测试");
        member.setPhone("52582");
        System.out.println(memberService.insertMember(member));
    }

    @Test
    public void deleteMemberById() {
        memberService.deleteMemberById(16);
    }

    @Test
    public void selectAllMember() {
        List<Member> memberList = memberService.selectAllMember();
        memberList.forEach(member -> System.out.println(member));
    }

    @Test
    public void getMemberById() {
        System.out.println(memberService.getMemberById(1));
    }
}