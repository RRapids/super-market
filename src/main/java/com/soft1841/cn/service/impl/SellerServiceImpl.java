package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.SellerDAO;
import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;

public class SellerServiceImpl implements SellerService {
    private SellerDAO sellerDAO = DAOFactory.getSellerDAOInstance();

    @Override
    public boolean login(String number, String password) {
        Seller seller = null;
        try {
            seller = sellerDAO.getSellerByNumber(number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据工号查找成功
        if (seller != null) {
            //比较密码
            if (password.equals(seller.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
