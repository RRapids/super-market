package com.soft1841.cn.dao.impl;
/**
 * 待修改
 */

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.MemberDAO;
import com.soft1841.cn.entity.Member;

import java.sql.SQLException;
import java.util.List;

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
                cn.hutool.db.Entity.create("t_member").set("id", id)
        );
    }

    @Override
    public List<Entity> selectAllMember() throws SQLException {
        return Db.use().query("SELECT * FROM t_member ");
    }

    @Override
    public Entity getMemberById(int id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_member WHERE id = ? ", id);
    }
}
