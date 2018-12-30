package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SellerDAOTest {
    private SellerDAO sellerDAO = DAOFactory.getSellerDAOInstance();

    /**
     * 收银员测试类
     * @throws SQLException
     * 2018.12.25
     */
    @Test
    public void getSellerByNumber()throws SQLException {
        Seller seller = sellerDAO.getSellerByNumber("r'yu'j");
        System.out.println(seller);
    }
}