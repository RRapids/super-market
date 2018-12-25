package com.soft1841.cn.utils;

import com.soft1841.cn.dao.*;
import com.soft1841.cn.dao.impl.*;

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

    public static AdminDAO getAdminDAOInstance() { return new AdminDAOImpl(); }

    public static MemberDAO getMemberDAOInstance() { return new MemberDAOImpl(); }

    public static DetailDAO getDetailDAOInstance(){return new DetailDAOImpl();}

    public static GoodsDAO getGoodsDAOInstance(){return new GoodsDAOImpl();}
}

