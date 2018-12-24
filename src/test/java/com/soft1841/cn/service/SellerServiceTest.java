package com.soft1841.cn.service;

import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class SellerServiceTest {

    private SellerService sellerService = ServiceFactory.getSellerServiceInstance();

    @Test
    public void login() {
        boolean flag = sellerService.login("184128", "hjl455");
        assertEquals(true, flag);
    }
}