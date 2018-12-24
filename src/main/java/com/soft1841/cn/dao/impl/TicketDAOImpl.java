package com.soft1841.cn.dao.impl;
/**
 * 未完成
 */

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.TicketDAO;
import com.soft1841.cn.entity.Detail;

import java.sql.SQLException;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public List<Detail> getAllTicket() throws SQLException {
        return null;
    }

    @Override
    public Entity getTicketById(int id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_receipt WHERE id = ? ",id);
    }

    @Override
    public Long insertTicket(Detail detail) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_receipt")
                        .set("cashier_time",detail.getTicketID())
        );
    }
}
