package com.soft1841.cn.utils;

import com.soft1841.cn.service.SellerService;
import com.soft1841.cn.service.impl.SellerServiceImpl;

public class ServiceFactory {
    public static SellerService getSellerServiceInstance() {
        return new SellerServiceImpl();
    }
}
