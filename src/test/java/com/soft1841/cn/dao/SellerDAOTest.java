package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SellerDAOTest {
    private SellerDAO sellerDAO = DAOFactory.getSellerDAOInstance();

    @Test
    public void getSellerByNumber()throws SQLException {
        Seller seller = sellerDAO.getSellerByNumber("184128");
        System.out.println(seller);
    }
}