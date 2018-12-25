package com.soft1841.cn.dao;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Goods;

import java.sql.SQLException;
import java.util.List;

public interface GoodsDAO {
    /**
     * 获取所有商品
     *
     * @return
     */
    List<Entity> getAllGoods() throws SQLException;

    /**
     * 新增商品
     *
     * @param goods
     */
    Long insertGoods(Goods goods) throws SQLException;

    /**
     * 根据id删除商品
     *
     * @param id
     */
    int deleteGoodsByID(long id) throws SQLException;

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    Entity getGoodsById(int id) throws SQLException;

    /**
     * 修改商品信息
     * @param goods
     */
    int updateGoods(Goods goods) throws SQLException;

}
