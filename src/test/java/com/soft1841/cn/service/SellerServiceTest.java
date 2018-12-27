package com.soft1841.cn.service;

import com.soft1841.cn.entity.Seller;
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

    @Test
    public void getSellerByNumber() {
        System.out.println(sellerService.getSellerByNumber("184113"));
    }

    @Test
    public void selectAllSellers(){
        System.out.println(sellerService.selectAllSellers());
    }

    @Test
    public void insertSeller(){
        Seller seller = new Seller();
        seller.setNumber("22222");
        seller.setPassword("58525");
        seller.setName("蜘蛛侠1");
        System.out.println(sellerService.insertSeller(seller));
    }

    @Test
    public void deleteSellerById(){
        sellerService.deleteSellerById(8);
    }
}