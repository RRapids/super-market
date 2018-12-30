package com.soft1841.cn.dao.impl;
/**
 * 管理员
 */

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import com.soft1841.cn.dao.AdminDAO;
import com.soft1841.cn.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员DAO
 *
 * @author 袁腾飞
 */
public class AdminDAOImpl implements AdminDAO {

    @Override
    public Admin getAdminByNumber(String number) throws SQLException {
        Entity entity = Db.use().queryOne("SELECT * FROM t_admin WHERE number = ? ", number);
        return convertAdmin(entity);
    }

    @Override
    public List<Admin> selectAllAdmins() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM t_admin ");
        List<Admin> adminList = new ArrayList<>();
        for (Entity entity : entityList) {
            adminList.add(convertAdmin(entity));
        }
        return adminList;
    }

    @Override
    public int deleteAdminById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_admin").set("admin_id", id)
        );
    }

    @Override
    public Long insertAdmin(Admin admin) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_admin")
                        .set("name", admin.getName())
                        .set("avatar", admin.getAvatar())
                        .set("number", admin.getNumber())
                        .set("password", admin.getPassword())
        );
    }

    @Override
    public int updateAdmin(Admin admin) throws SQLException {
        //只改密码
        return Db.use().update(
                Entity.create().set("password", admin.getPassword()),
                Entity.create("t_admin").set("admin_id", admin.getId())
        );
    }

    @Override
    public List<Admin> selectAdminByName(String keywords) throws SQLException {
        List<Entity> entityList = Db.use().findLike("t_admin", "name", keywords, Condition.LikeType.Contains);
        List<Admin> adminList = new ArrayList<>();
        for (Entity entity : entityList) {
            adminList.add(convertAdmin(entity));
        }
        return adminList;
    }

    /**
     * 将Entity转换为Type类型
     *
     * @param entity
     * @return Admin
     */
    private Admin convertAdmin(Entity entity) {
        Admin admin = new Admin();
        admin.setId(entity.getLong("admin_id"));
        admin.setAvatar(entity.getStr("avatar"));
        admin.setName(entity.getStr("name"));
        admin.setPassword(entity.getStr("password"));
        admin.setNumber(entity.getStr("number"));
        return admin;
    }


}

