package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {
    /**
     * 根据工号查询管理员
     *
     * @param number
     * @return
     * @throws SQLException
     */
    Admin getAdminByNumber(String number) throws SQLException;

    /**
     * 查询所有
     */
    List<Admin> selectAllAdmins() throws SQLException;

    /**
     * 删除
     */
    int deleteAdminById(long id) throws SQLException;

    /**
     * 新增
     */
    Long insertAdmin(Admin admin) throws SQLException;

    /**
     * 修改收银员信息
     */
    int updateAdmin(Admin admin) throws SQLException;

    /**
     * 根据姓名查
     */
    List<Admin> selectAdminByName(String keywords) throws SQLException;

    int countAdmins() throws SQLException;
}
