package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.MemberDAO;
import com.soft1841.cn.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 会员DAO
 *
 * @author 汤越
 */
public class MemberDAOImpl implements MemberDAO {
    @Override
    public Long insertMember(Member member) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_member")
                        .set("name", member.getName())
                        .set("address", member.getAddress())
                        .set("phnoe_number", member.getPhone())
        );
    }

    @Override
    public int deleteMemberById(long id) throws SQLException {
        return Db.use().del(
                cn.hutool.db.Entity.create("t_member").set("memberID", id)
        );
    }

    @Override
    public List<Member> selectAllMember() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM t_member");
        List<Member> memberList = new ArrayList<>();
        for (Entity entity : entityList) {
            memberList.add(convertMember(entity));
        }
        return memberList;
    }

    @Override
    public Member getMemberById(long id) throws SQLException {
        Entity entity = Db.use().queryOne("SELECT * FROM t_member WHERE memberID = ? ", id);
        return convertMember(entity);
    }

    /**
     * 将Entity转换为Type类型
     *
     * @param entity
     * @return Member
     */
    private Member convertMember(Entity entity) {
        Member member = new Member();
        member.setId(entity.getLong("memberID"));
        member.setName(entity.getStr("name"));
        member.setAddress(entity.getStr("address"));
        member.setPhone(entity.getStr("phnoe_number"));
        member.getIntegral();
        return member;
    }
}
