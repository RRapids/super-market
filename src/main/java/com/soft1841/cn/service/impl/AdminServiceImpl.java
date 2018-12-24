package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.AdminDAO;
import com.soft1841.cn.entity.Admin;
import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.service.AdminService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO = (AdminDAO) DAOFactory.getAdminDAOInstance();

    @Override
    public boolean login(String number, String password) {
        Admin admin = null;
        try {
            admin = adminDAO.getAdminByNumber(number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据工号查找成功
        if (admin != null) {
            //比较密码
            if (password.equals(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
