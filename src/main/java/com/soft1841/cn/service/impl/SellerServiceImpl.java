package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.SellerDAO;
import com.soft1841.cn.entity.Seller;
import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SellerServiceImpl implements SellerService {
    private SellerDAO sellerDAO = DAOFactory.getSellerDAOInstance();

    @Override
    public boolean login(String number, String password) {
        Seller seller = null;
        try {
            seller = sellerDAO.getSellerByNumber(number);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

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

    @Override
    public Seller getSellerByNumber(String number) {
        Seller seller = new Seller();
        try {
            seller = sellerDAO.getSellerByNumber(number);
        } catch (SQLException e) {
            System.err.println("查询单个收银员出现异常!");
        } catch (NullPointerException e) {

        }
        return seller;
    }

    @Override
    public List<Seller> selectAllSellers() {
        List<Seller> sellerList = new ArrayList<>();
        try {
            sellerList = sellerDAO.selectAllSellers();
        } catch (SQLException e) {
            System.err.println("查询所有收银员信息出现异常");
        }
        return sellerList;
    }

    @Override
    public void deleteSellerById(long id) {
        try {
            sellerDAO.deleteSellerById(id);
        } catch (SQLException e) {
            System.err.println("删除收银员信息出现异常");
        }
    }

    @Override
    public Long insertSeller(Seller seller) {
        long result = 0;
        try {
            result = sellerDAO.insertSeller(seller);
        } catch (SQLException e) {
            System.err.println("新增收银员信息出现异常");
        }
        return result;
    }

    @Override
    public void updateSeller(Seller seller) {
        try {
            sellerDAO.updateSeller(seller);
        } catch (SQLException e) {
            System.err.println("修改收银员密码异常");
        }
    }

    @Override
    public List<Seller> getSellerByName(String keywords) {
        List<Seller> sellerList = new ArrayList<>();
        try {
            sellerList = sellerDAO.selectSellersByName(keywords);
        } catch (SQLException e) {
            System.err.println("根据名字查询收银员信息出现异常");
        }
        return sellerList;
    }

}