package com.soft1841.cn.dao;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {
    /**
     * 新增类别, 返回自增主键(Long)
     * @param member
     * @return
     */
    Long insertMember(Member member) throws SQLException;

    /**
     * 根据id删除类别
     * @param id
     * @return
     */
    int deleteMemberById(long id) throws SQLException;

    /**
     * 查询所有类别
     * @return
     */
    List<Entity> selectAllMember() throws SQLException;

    /**
     * 根据id查询类别信息
     * @param id
     * @return
     */
    Entity getMemberById(int id) throws SQLException;
}
