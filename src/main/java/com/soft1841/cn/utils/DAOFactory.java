package com.soft1841.cn.utils;

import com.soft1841.cn.dao.AdminDAO;
import com.soft1841.cn.dao.SellerDAO;
import com.soft1841.cn.dao.impl.AdminDAOImpl;
import com.soft1841.cn.dao.impl.SellerDAOImpl;

/**
 * DAO工厂类
 */
public class DAOFactory {
    public static SellerDAO getSellerDAOInstance() {
        return new SellerDAOImpl();
    }
    public static AdminDAO getAdminDAOInstance(){
        return new AdminDAOImpl();
    }
}
