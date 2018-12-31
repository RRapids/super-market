package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import com.soft1841.cn.dao.GoodsDAO;
import com.soft1841.cn.entity.Goods;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品DAO
 *
 * @author 袁腾飞
 */
public class GoodsDAOImpl implements GoodsDAO {

    @Override
    public List<Goods> selectGoods() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM t_goods");
        List<Goods> goodsList = new ArrayList<>();
        for (Entity entity : entityList) {
            goodsList.add(convertGoods(entity));

        }
        return goodsList;

    }

    @Override
    public Long insertGoods(Goods goods) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_goods")
                        .set("name", goods.getName())
                        .set("price", goods.getPrice())
                        .set("avatar", goods.getAvatar())
                        .set("description", goods.getDescription())
                        .set("barCode", goods.getBarCode())
                        .set("quantity", goods.getQuantity())
                        .set("type_id", goods.getTypeId())
        );
    }


    @Override
    public int deleteGoodsByID(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_goods").set("goods_id", id)
        );
    }

    @Override
    public List<Goods> getGoodsById(long id) throws SQLException {
        Entity entity = Db.use().queryOne("SELECT * FROM t_goods WHERE goods_id = ?" ,id);
        return (List<Goods>) convertGoods(entity);
    }

    @Override
    public int updateGoods(Goods goods) throws SQLException {
        return Db.use().update(
                Entity.create().set("price", goods.getPrice())
                        .set("quantity", goods.getQuantity())
                        .set("description", goods.getDescription())
                        .set("barCode", goods.getBarCode()),
                Entity.create("t_goods").set("goods_id", goods.getId())
        );

    }

    @Override
    public List<Goods> getGoodsByTypeId(long typeId) throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM t_goods WHERE type_id = ?",typeId);
        List<Goods> goodsList = new ArrayList<>();
        for (Entity entity : entityList) {
            goodsList.add(convertGoods(entity));

        }
        return goodsList;
    }

    @Override
    public List<Goods> selectBooksLike(String keywords) throws SQLException {
        List<Entity> entityList = Db.use().findLike("t_goods","name",keywords, Condition.LikeType.Contains);
        List<Goods> goodsList = new ArrayList<>();
        for (Entity entity:entityList) {
            goodsList.add(convertGoods(entity));
        }
        return goodsList;
    }

    @Override
    public Goods getGoodsByBarCode(String barCode) throws SQLException {
        return null;
    }

    @Override
    public int countByType(long typeId) throws SQLException {
         return Db.use().queryNumber("SELECT COUNT(*) FROM t_goods WHERE type_id=?",typeId).intValue();
    }

    @Override
    public int countGoods() throws SQLException {
        return Db.use().queryNumber("SELECT COUNT(*) FROM t_goods").intValue();

    }

//    @Override
//    public Goods getGoodsByBarCode(String barCode) throws SQLException {
//        List<Entity> entityList = Db.use().findLike("t_goods","barCode",barCode, Condition.LikeType.Contains);
//        List<Goods> goodsList = new ArrayList<>();
//        for (Entity entity:entityList) {
//            goodsList.add(convertGoods(entity));
//        }
//        return goodsList;
//    }

    private Goods convertGoods(Entity entity) {
        Goods goods = new Goods();
        goods.setId(entity.getLong("goods_id"));
        goods.setName(entity.getStr("name"));
        goods.setAvatar(entity.getStr("avatar"));
        goods.setTypeId(entity.getLong("type_id"));
        goods.setPrice(entity.getStr("price"));
        goods.setBarCode(entity.getStr("barCode"));
        goods.setQuantity(entity.getStr("quantity"));
        goods.setDescription(entity.getStr("description"));
        return goods;
    }
}