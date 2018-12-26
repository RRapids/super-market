package com.soft1841.cn.utils;

import com.soft1841.cn.entity.Type;
import com.soft1841.cn.service.AdminService;
import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.service.TypeService;
import com.soft1841.cn.service.impl.AdminServiceImpl;
import com.soft1841.cn.service.impl.SellerServiceImpl;
import com.soft1841.cn.service.impl.TypeServiceImpl;

public class ServiceFactory {
    public static SellerService getSellerServiceInstance() {

        return new SellerServiceImpl();
    }

    public static AdminService getAdminServiceInstance() {
        return new AdminServiceImpl();
    }

    public static TypeService getTypeServiceInstance() {
        return new TypeServiceImpl();
    }


}
