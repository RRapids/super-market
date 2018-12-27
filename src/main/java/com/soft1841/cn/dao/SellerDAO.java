package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Seller;

import java.sql.SQLException;
import java.util.List;


public interface SellerDAO {

    /**
     * 根据工号查询收银员
     * @param number
     * @return
     * @throws SQLException
     */
    Seller getSellerByNumber(String number)throws SQLException;

    /**
     * 查询所有收银员
     * @return List<Seller>
     * @throws SQLException
     */
    List<Seller> selectAllSellers()throws SQLException;

    /**
     * 根据id删除
     */
    int deleteSellerById(long id) throws SQLException;

    /**
     * 新增
     */
    Long insertSeller(Seller seller) throws SQLException;
}
