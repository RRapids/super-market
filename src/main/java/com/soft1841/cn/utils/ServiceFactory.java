package com.soft1841.cn.utils;

import com.soft1841.cn.entity.Type;
import com.soft1841.cn.service.*;
import com.soft1841.cn.service.impl.*;

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

    public static MemberService getMemberServiceInstance() {
        return new MemberServiceImpl();
    }

    public static TicketService getTicketServiceInstance() {
        return new TicketServiceImpl();
    }
    public static GoodsService getGoodsServiceInstance(){
        return new GoodsServiceImpl();
    }

    public static DetailService getDetailServiceInstance() {
        return new DetailServiceImpl();
    }
}
