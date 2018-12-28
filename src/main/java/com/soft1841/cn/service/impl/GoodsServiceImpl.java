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
    private GoodsDAO goodsDAO = DAOFactory.getGoodsDAOInstance();
    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goodsList = new ArrayList<>();
        try {
            goodsList = goodsDAO.selectGoods();
        } catch (SQLException e) {
            System.err.println("查询所有商品信息出现异常");
        }
        return goodsList;
    }

    @Override
    public Long addGoods(Goods goods) {
        long result = 0;
        try {
            result = goodsDAO.insertGoods(goods);
        } catch (SQLException e) {
            System.err.println("新增商品信息出现异常");
        }
        return result;
    }

    @Override
    public void deleteGoodsByID(long id) {
        try {
            goodsDAO.deleteGoodsByID(id);
        } catch (SQLException e) {
            System.err.println("删除商品信息出现异常");
        }

    }

    @Override
    public List<Goods> getGoodsById(long id) {
        List<Goods> goodsList = new ArrayList<>();
        try {
            goodsList = goodsDAO.getGoodsById(id);
        } catch (SQLException e) {
            System.err.println("通过id查询商品出现异常");
        }

        return goodsList;
    }


    @Override
    public void updateGoods(Goods goods) {
        try {
            goodsDAO.updateGoods(goods);
        } catch (SQLException e) {
            System.err.println("更新商品信息出现异常");
        }
    }

    @Override
    public List<Goods> getGoodsLike(String keywords) {
        List<Goods> goodsList = new ArrayList<>();
        try {
            goodsList = goodsDAO.selectBooksLike(keywords);
        } catch (SQLException e) {
            System.err.println("根据关键字查询商品信息出现异常");
        }
        return goodsList;
    }

    @Override
    public List<Goods> getGoodsByTypeId(long typeId) {
        List<Goods> goodsList = new ArrayList<>();
        try {
            goodsList = goodsDAO.getGoodsByTypeId(typeId);
        } catch (SQLException e) {
            System.err.println("通过类别增加商品出现异常");
        }
        return goodsList;
    }

    @Override
    public List<Goods> getGoodsByBarCode(String barCode) {
        List<Goods> goodsList = new ArrayList<>();
        try {
            goodsList = goodsDAO.getGoodsByBarCode(barCode);
        } catch (SQLException e) {
            System.err.println("根据关键字查询商品信息出现异常");
        }
        return goodsList;
    }

}