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
                Entity.create("t_type")
                        .set("name", member.getName())
        );
    }

    @Override
    public int deleteMemberById(long id) throws SQLException {
        return 0;
    }

    @Override
    public List<Entity> selectAllMember() throws SQLException {
        return null;
    }

    @Override
    public Entity getMemberById(int id) throws SQLException {
        return null;
    }
}
