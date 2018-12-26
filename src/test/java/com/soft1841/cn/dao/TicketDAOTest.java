package com.soft1841.cn.dao;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.entity.Ticket;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 小票
 *
 * @author 腾飞
 * 2018.12.26
 */
public class TicketDAOTest {
    //通过DAO工厂获得TicketDAO的实例
    private TicketDAO ticketDAO = DAOFactory.getTicketDAOInstance();

    @Test
    public void getAllTicket() throws SQLException {
        List<Entity> ticketList = ticketDAO.getAllTicket();
        ticketList.forEach(ticket -> System.out.println(ticket));

    }

    @Test
    public void getTicketById() throws SQLException {
        Entity ticket = ticketDAO.getTicketById(1);
        System.out.println(ticket);

    }

    @Test
    public void insertTicket()throws SQLException{

    }
}