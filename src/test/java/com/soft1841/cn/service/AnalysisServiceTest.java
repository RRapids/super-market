package com.soft1841.cn.service;

import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;

public class AnalysisServiceTest {
    private AnalysisService analysisService = ServiceFactory.getAnalysisServiceInstance();

    @Test
    public void getTypesCount() {
        int n = analysisService.getTypesCount();
        System.out.println(n);
    }

    @Test
    public void getGoodsCount() {
        int n = analysisService.getGoodsCount();
        System.out.println(n);
    }

    @Test
    public void getAdminsCount(){
        int n = analysisService.getAdminsCount();
        System.out.println(n);
    }
    @Test
    public void getMembersCount(){
        int n = analysisService.getMembersCount();
        System.out.println(n);
    }
}