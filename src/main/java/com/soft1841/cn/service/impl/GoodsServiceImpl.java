package com.soft1841.cn.service.impl;
import com.soft1841.cn.dao.GoodsDAO;
import com.soft1841.cn.entity.Goods;
import com.soft1841.cn.service.GoodsService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品服务接口
 * @author 腾飞
 */
public class GoodsServiceImpl implements GoodsService {
    @Override
    public List<Goods> getAllGoods() {
        return null;
    }

    @Override
    public Long insertGoods(Goods goods) {
        return null;
    }

    @Override
    public int deleteGoodsByID(long id) {
        return 0;
    }

    @Override
    public Goods getGoodsById(long id) {
        return null;
    }

    @Override
    public int updateGoods(Goods goods) {
        return 0;
    }

    @Override
    public List<Goods> getGoodsByTypeId(long typeId) {
        return null;
    }
}