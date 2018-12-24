package com.soft1841.cn.utils;

import com.soft1841.cn.dao.AdminDAO;
import com.soft1841.cn.dao.SellerDAO;
import com.soft1841.cn.dao.TypeDAO;
import com.soft1841.cn.dao.impl.AdminDAOImpl;
import com.soft1841.cn.dao.impl.SellerDAOImpl;
import com.soft1841.cn.dao.impl.TypeDAOImpl;

/**
 * DAO工厂类
 */
public class DAOFactory {
    public static SellerDAO getSellerDAOInstance() {
        return new SellerDAOImpl();
    }

    public static TypeDAO getTypeDAOInstance() {
        return new TypeDAOImpl();
    }

    public static AdminDAO getAdminDAOInstance(){
        return new AdminDAOImpl();
    }
}
