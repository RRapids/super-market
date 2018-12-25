package com.soft1841.cn.dao;

import cn.hutool.db.Entity;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class GoodsDAOTest {
    private GoodsDAO goodsDAO = DAOFactory.getGoodsDAOInstance();

    @Test
    public void getAllGoods() throws SQLException {
        List<Entity> goodsList = goodsDAO.getAllGoods();
        goodsList.forEach(entity -> System.out.println(entity));
    }

}