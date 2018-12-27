package com.soft1841.cn.service;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.entity.Ticket;
import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * 小票
 * 待完成
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
        //
    @Test
    public void getTicketById() {

    }


    //增待修改
    @Test
    public void insertTicket() {
        Ticket ticket = new Ticket();
        ticket.setMemberID(5);
        ticket.setSellerID(5);
        ticketService.insertTicket(ticket);
    }
}