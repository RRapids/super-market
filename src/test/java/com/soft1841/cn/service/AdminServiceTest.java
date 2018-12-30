package com.soft1841.cn.service;

import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminServiceTest {
    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    @Test
    public void login(){
        boolean flag = adminService.login("1","2");
        assertEquals(true,flag);
    }
}