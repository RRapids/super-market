package com.soft1841.cn.dao;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO {
    /**
     * 查所有
     * @return
     */
    List<Ticket> getAllTicket() throws SQLException;

    /**
     * 根据id查询类别信息
     * @param id
     * @return
     */
    Entity getTicketById(long id) throws SQLException;

    /**
     * 新增类别, 返回自增主键(Long)
     * @param ticket
     * @return
     */
    Long insertTicket(Ticket ticket) throws SQLException;
}
