package com.soft1841.cn.service;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Goods;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 腾飞
 */
public interface GoodsService {
    /**
     * 获取所有商品
     *
     * @return
     */
    List<Goods> getAllGoods();

    /**
     * 新增商品
     *
     * @param goods
     */
    Long insertGoods(Goods goods);

    /**
     * 根据id删除商品
     *
     * @param id
     */
    int deleteGoodsByID(long id);

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    Goods getGoodsById(long id);

    /**
     * 修改商品信息
     *
     * @param goods
     */
    int updateGoods(Goods goods);

    /**
     * 根据商品类别查询商品
     * @param typeId
     * @return
     * @throws SQLException
     */
    List<Goods> getGoodsByTypeId(long typeId);

}
