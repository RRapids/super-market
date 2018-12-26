package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.TicketDAO;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.entity.Ticket;
import com.soft1841.cn.service.TicketService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 小票
 * @author 腾飞
 * 2018.12.26
 */
public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO = DAOFactory.getTicketDAOInstance();

    @Override
    public List<Ticket> getAllTicket() {
        List<Ticket> ticketList = new ArrayList<>();
        try {
            ticketList = ticketDAO.getAllTicket();
        } catch (SQLException e) {
            //友好处理异常
            System.err.println("查询所有小票出现异常!");
        }
        return ticketList;
    }

    @Override
    public Ticket getTicketById(int id) {
        Ticket ticket = new Ticket();
        try {
            ticket = ticketDAO.getTicketById(id);
        } catch (SQLException e) {
            System.err.println("查询单个小票出现异常!");
        }
        return ticket;
    }

    @Override
    public Long insertTicket(Detail detail) {
        long result = 0;
        try {
            result = ticketDAO.insertTicket(detail);
        } catch (SQLException e) {
            System.err.println("新增小票出现异常!");
        }
        return result;
    }

}
