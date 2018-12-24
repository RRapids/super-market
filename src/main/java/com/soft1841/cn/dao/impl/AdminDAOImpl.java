package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.AdminDAO;
import com.soft1841.cn.entity.Admin;

import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public Admin getAdminByNumber(String number) throws SQLException {
        Entity entity =  Db.use().queryOne("SELECT * FROM t_admin WHERE number = ? ",number );
        return convertAdmin(entity);
    }
    private Admin convertAdmin(Entity entity){
        Admin admin = new Admin(entity.getLong("id"),entity.getStr("name"),entity.getStr("avatar"),entity.getStr("password"),entity.getStr("number"));
        return admin;
    }
}
