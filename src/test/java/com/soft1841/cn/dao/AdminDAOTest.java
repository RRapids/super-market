package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Admin;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

public class AdminDAOTest {
    private AdminDAO adminDAO = (AdminDAO) DAOFactory.getAdminDAOInstance();
    @Test
    public void getAdminByNumber() throws SQLException {
        Admin admin = adminDAO.getAdminByNumber("2649794");
        System.out.println(admin);
    }
}