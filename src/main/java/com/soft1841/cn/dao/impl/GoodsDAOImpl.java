package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.GoodsDAO;
import com.soft1841.cn.entity.Goods;

import java.sql.SQLException;
import java.util.List;

public class GoodsDAOImpl implements GoodsDAO {

    @Override
    public List<Entity> getAllGoods() throws SQLException {
        return Db.use().query("SELECT * FROM t_goods");
    }

    @Override
    public Long insertGoods(Goods goods) throws SQLException {
        return Db.use().insertForGeneratedKey(
                cn.hutool.db.Entity.create("t_goods")
                        .set("name", goods.getName())
                        .set("typeID", goods.getTypeID())
                        .set("barCode", goods.getBarCode())
                        .set("price", goods.getPrice())
                        .set("avatar", goods.getAvatar())
                        .set("quantity", goods.getQuantity())
                        .set("description", goods.getDescription())
        );
    }

    @Override
    public int deleteGoodsByID(int id) throws SQLException {
        return Db.use().del(
                cn.hutool.db.Entity.create("t_goods").set("id", id)
        );
    }

    @Override
    public Entity getGoodsById(int id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_goods WHERE id = ?", id);
    }

    @Override
    public int updateGoods(Goods goods) throws SQLException {
        return Db.use().update(
                Entity.create().set("name", goods.getName())
                        .set("typeID", goods.getTypeID())
                        .set("barCode", goods.getBarCode())
                        .set("price", goods.getPrice())
                        .set("picture", goods.getAvatar())
                        .set("quantity", goods.getQuantity())
                        .set("description", goods.getDescription()),
                Entity.create("t_goods").set("id", goods.getId())

        );

    }
}