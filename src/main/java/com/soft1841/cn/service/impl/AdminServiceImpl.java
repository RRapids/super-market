package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.AdminDAO;
import com.soft1841.cn.entity.Admin;
import com.soft1841.cn.service.AdminService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO =DAOFactory.getAdminDAOInstance();

    @Override
    public boolean login(String number, String password) {
        Admin admin = null;
        try {
            admin = adminDAO.getAdminByNumber(number);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){

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

    @Override
    public Admin getAdminByNumber(String number) {
        Admin admin = new Admin();
        try {
            admin = adminDAO.getAdminByNumber(number);
        } catch (SQLException e) {
            System.err.println("查询单个管理员出现异常!");
        } catch (NullPointerException e) {

        }
        return admin;    }

    @Override
    public List<Admin> selectAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        try {
            adminList = adminDAO.selectAllAdmins();
        } catch (SQLException e) {
            System.err.println("查询所有管理员信息出现异常");
        }
        return adminList;    }

    @Override
    public void deleteAdminById(long id) {
        try {
            adminDAO.deleteAdminById(id);
        } catch (SQLException e) {
            System.err.println("删除管理员信息出现异常");
        }
    }

    @Override
    public Long insertAdmin(Admin admin) {
        long result = 0;
        try {
            result = adminDAO.insertAdmin(admin);
        } catch (SQLException e) {
            System.err.println("新增管理员信息出现异常");
        }
        return result;
    }

    @Override
    public void updateAdmin(Admin admin) {
        try {
            adminDAO.updateAdmin(admin);
        } catch (SQLException e) {
            System.err.println("修改管理员密码异常");
        }
    }
}
