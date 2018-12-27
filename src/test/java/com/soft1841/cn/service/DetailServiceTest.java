package com.soft1841.cn.service;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

public class DetailServiceTest {
    private DetailService detailService = ServiceFactory.getDetailServiceInstance();

    @Test
    public void getAllDetails(){
        List<Detail> detailList = detailService.getAllDetails();
        detailList.forEach(detail -> System.out.println(detail));
    }

    @Test
    public void getDetailsById(){
        System.out.println(detailService.getDetailById(2));
    }

    @Test
    public void insertTicket(){
        Detail detail = new Detail();
        detail.setTicketID(2);
        detail.setBarCode("956239");
        detail.setNumber("5");
        detailService.insertDetail(detail);
    }
}