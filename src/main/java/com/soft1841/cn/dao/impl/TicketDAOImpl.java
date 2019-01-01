package com.soft1841.cn.dao.impl;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.TicketDAO;
import com.soft1841.cn.entity.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 小票DAO
 * @author 汤越
 */
public class TicketDAOImpl implements TicketDAO {

    @Override
    public List<Ticket> getAllTicket() throws SQLException {
        //查询得到List<Entity>
        List<Entity> entityList = Db.use().query("SELECT * FROM t_ticket ");
        //创建一个List<Type>，存放具体的图书类别
        List<Ticket> ticketList = new ArrayList<>();
        //遍历entityList，转换为typeList
        for (Entity entity : entityList) {
            ticketList.add(convertTicket(entity));
        }
        return ticketList;    }

    @Override
    public Entity getTicketById(long id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_ticket WHERE ticket_id = ? ", id);
    }

    @Override
    public Long insertTicket(Ticket ticket) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_ticket")
                        .set("sellerID", ticket.getSellerID())
        );
    }

    private Ticket convertTicket(Entity entity) {
        Ticket ticket = new Ticket();
        ticket.setId(entity.getLong("ticket_id"));
        ticket.setSellerID(entity.getLong("sellerID"));
        ticket.setMemberID(entity.getLong("memberID"));
        ticket.setCollectDate(entity.getDate("collectDate"));
        ticket.setTotal(entity.getStr("total"));
        return ticket;
    }
}
