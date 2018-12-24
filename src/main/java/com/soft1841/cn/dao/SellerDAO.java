package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Seller;

import java.sql.SQLException;


public interface SellerDAO {

    /**
     * 根据工号查询收银员
     * @param number
     * @return
     * @throws SQLException
     */
    Seller getSellerByNumber(String number)throws SQLException;
}
