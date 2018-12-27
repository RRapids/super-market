package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.GoodsDAO;
import com.soft1841.cn.entity.Goods;

import java.sql.SQLException;
import java.util.List;
/**
 * 商品DAO
 * @author 袁腾飞
 */
public class GoodsDAOImpl implements GoodsDAO {
    @Override
    public List<Entity> getAllGoods() throws SQLException {
        return Db.use().query("SELECT * FROM t_goods");
    }

    @Override
    public Long insertGoods(Goods goods) throws SQLException {
        return Db.use().insertForGeneratedKey(
                cn.hutool.db.Entity.create("t_goods")
                        .set("name",goods.getName())
                        .set("type_id",goods.getTypeId())
                        .set("barCode",goods.getBarCode())
                        .set("price",goods.getPrice())
                        .set("avatar",goods.getAvatar())
                        .set("quantity",goods.getQuantity())
                        .set("description",goods.getDescription())
        );
    }


    @Override
    public int deleteGoodsByID(long id) throws SQLException {
        return Db.use().del(
                cn.hutool.db.Entity.create("t_goods").set("goods_id",id)
        );
    }

    @Override
    public Entity getGoodsById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_goods WHERE goods_id = ?",id);
    }

    @Override
    public int updateGoods(Goods goods) throws SQLException {
        return Db.use().update(
                Entity.create().set("price",goods.getPrice())
                        .set("avatar",goods.getAvatar())
                        .set("quantity",goods.getQuantity())
                        .set("description",goods.getDescription())
                        .set("barCode",goods.getBarCode()),
                Entity.create("t_goods").set("goods_id",goods.getId())
        );

    }

    @Override
    public List<Goods> getGoodsByTypeId(long typeId) throws SQLException {
        return null;
    }
}