package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.SellerDAO;
import com.soft1841.cn.entity.Seller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 收银员DAO
 *
 * @author 袁腾飞
 */
public class SellerDAOImpl implements SellerDAO {

    @Override
    public Seller getSellerByNumber(String number) throws SQLException {
        Entity entity = Db.use().queryOne("SELECT * FROM t_cashier WHERE number = ? ", number);
        return convertSeller(entity);
    }

    @Override
    public List<Seller> selectAllSellers() throws SQLException {
        List<Entity> entityList = Db.use().query("SELECT * FROM t_cashier ");
        List<Seller> sellerList = new ArrayList<>();
        for (Entity entity : entityList) {
            sellerList.add(convertSeller(entity));
        }
        return sellerList;
    }

    @Override
    public int deleteSellerById(long id) throws SQLException {
        return Db.use().del(
                Entity.create("t_cashier").set("sellerID", id)
        );
    }

    @Override
    public Long insertSeller(Seller seller) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_cashier")
                        .set("name", seller.getName())
                        .set("avatar", seller.getAvatar())
                        .set("number", seller.getNumber())
                        .set("password", seller.getPassword())
        );
    }

//    private Seller convertSeller(Entity entity) {
//        Seller seller = new Seller(entity.getLong("sellerID"), entity.getStr("number"),
//                entity.getStr("name"), entity.getStr("password"), entity.getStr("avatar"));
//        return seller;
//    }

    /**
     * 将Entity转换为Type类型
     *
     * @param entity
     * @return Seller
     */
    private Seller convertSeller(Entity entity) {
        Seller seller = new Seller();
        seller.setId(entity.getLong("sellerID"));
        seller.setAvatar(entity.getStr("avatar"));
        seller.setName(entity.getStr("name"));
        seller.setPassword(entity.getStr("password"));
        seller.setNumber(entity.getStr("number"));
        return seller;
    }
}
