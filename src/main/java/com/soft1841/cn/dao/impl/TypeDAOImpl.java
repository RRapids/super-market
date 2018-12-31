package com.soft1841.cn.dao.impl;
/**
 * 类型
 */

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.TypeDAO;
import com.soft1841.cn.entity.Type;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类型DAO
 *
 * @author 袁腾飞
 */
public class TypeDAOImpl implements TypeDAO {
    @Override
    public Long insertType(Type type) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_type")
                        .set("name", type.getTypeName())
        );
    }

    @Override
    public int deleteTypeById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_type").set("type_id", id)
        );
    }


    @Override
    public List<Type> selectAllTypes() throws SQLException {
        //查询得到List<Entity>
        List<Entity> entityList = Db.use().query("SELECT * FROM t_type ");
        //创建一个List<Type>，存放具体的图书类别
        List<Type> typeList = new ArrayList<>();
        //遍历entityList，转换为typeList
        for (Entity entity : entityList) {
            typeList.add(convertType(entity));
        }
        return typeList;
    }


    @Override
    public Type getTypeById(long id) throws SQLException {
        //采用自定义带参查询语句，返回单个实体
        Entity entity = Db.use().queryOne("SELECT * FROM t_type WHERE type_id = ? ", id);
        //将Entity转换为Type类型返回
        return convertType(entity);
    }

    @Override
    public int countTypes() throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM t_type").intValue();
    }


    /**
     * 将Entity转换为Type类型
     *
     * @param entity
     * @return Type
     */
    private Type convertType(Entity entity) {
        Type type = new Type();
        type.setId(entity.getLong("type_id"));
        type.setTypeName(entity.getStr("name"));
        return type;
    }
}
