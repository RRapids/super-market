package com.soft1841.cn.dao;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Goods;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品测试类
 *
 * @author 袁腾飞
 * 2018.12.25
 */
public class GoodsDAOTest {
    private GoodsDAO goodsDAO = DAOFactory.getGoodsDAOInstance();

    @Test
    public void selectGoods() throws SQLException {
        List<Goods> goodsList = goodsDAO.selectGoods();
        goodsList.forEach(entity -> System.out.println(entity));
    }

    @Test
    public void insertGoods() throws SQLException {

    }

    @Test
    public void deleteGoodsByID() throws SQLException {
        goodsDAO.deleteGoodsByID(2);
    }

    @Test
    public void getGoodsById() throws SQLException {
        goodsDAO.getGoodsById(3);
        System.out.println();
    }

    @Test
    public void updateGoods() throws SQLException {
    }

    @Test
    public void getGoodsByTypeId() throws SQLException {
        List<Goods> count = goodsDAO.getGoodsByTypeId(2);
        System.out.println(count);
    }
}