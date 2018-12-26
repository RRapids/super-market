package com.soft1841.cn.service;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.entity.Ticket;
import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;

import java.util.List;

/**
 * 小票
 * @author 腾飞
 * 2018.12.26
 */
public class TicketServiceTest {
    private TicketService ticketService = ServiceFactory.getTicketServiceInstance();

    @Test
    public void getAllTicket() {
        List<Entity> ticketList = ticketService.getAllTicket();
        ticketList.forEach(ticket -> System.out.println(ticket));
    }

    @Test
    public void getTicketById() {

    }

    @Test
    public void insertTicket() {
        Detail detail = new Detail();
        detail.setNumber("测试");
        ticketService.insertTicket(detail);
    }
}