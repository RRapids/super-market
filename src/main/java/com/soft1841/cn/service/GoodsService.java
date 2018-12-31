package com.soft1841.cn.service;

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

    boolean barCodeEnter(String barCode);

    /**
     * 新增商品
     *
     * @param goods
     */
    Long addGoods(Goods goods);

    /**
     * 根据id删除商品
     *
     * @param id
     */
    void deleteGoodsByID(long id);

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    List<Goods> getGoodsById(long id);

    /**
     * 修改商品信息
     *
     * @param goods
     */
    void updateGoods(Goods goods);

    /**
     * 根据商品名模糊查找
     * @param keywords
     * @return
     */
    List<Goods> getGoodsLike(String keywords);

    /**
     * 根据商品类别查询商品
     * @param typeId
     * @return
     * @throws SQLException
     */
    List<Goods> getGoodsByTypeId(long typeId);

    /**
     * 根据条码查询商品
     * @param barCode
     * @return
     */
    List<Goods> getGoodsByBarCode(String barCode);


    int countByType(long typeId);


}
