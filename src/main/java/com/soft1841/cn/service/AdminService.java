package com.soft1841.cn.service;

import com.soft1841.cn.entity.Admin;

import java.util.List;

/**
 * 管理员业务逻辑接口
 * @author 袁腾飞
 */
public interface AdminService {
    /**
     * 登录功能
     * @param number
     * @param password
     * @return
     */
    boolean login(String number, String password);
    /**
     * 根据工号查询
     *
     * @param number
     * @return
     */
    Admin getAdminByNumber(String number);

    /**
     * 查询所有
     */
    List<Admin> selectAllAdmins();

    /**
     * 根据id删除
     */
    void deleteAdminById(long id);

    /**
     * 新增
     */
    Long insertAdmin(Admin admin);

    /**
     * 修改密码
     */
    void updateAdmin(Admin admin);
}