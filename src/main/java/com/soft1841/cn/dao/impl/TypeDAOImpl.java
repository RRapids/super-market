package com.soft1841.cn.dao.impl;
/**
 * 类型
 */

import cn.hutool.db.Db;
import com.soft1841.cn.dao.TypeDAO;
import com.soft1841.cn.entity.Type;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.List;
/**
 * 类型DAO
 * @author 袁腾飞
 */
public class TypeDAOImpl implements TypeDAO {
    @Override
    public Long insertType(Type type) throws SQLException {
        return Db.use().insertForGeneratedKey(
                cn.hutool.db.Entity.create("t_type")
                        .set("name", type.getTypeName())
        );
    }

    @Override
    public int deleteTypeById(long id) throws SQLException {
        return Db.use().del(
                cn.hutool.db.Entity.create("t_type").set("id", id)
        );
    }

    @Override
    public List<Entity> selectAllTypes() throws SQLException {
        return Db.use().query("SELECT * FROM t_type ");
    }

    @Override
    public Entity getTypeByName(String name) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_type WHERE id = ? ", name);
    }
}
