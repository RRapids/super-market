package com.soft1841.cn.dao.impl;
/**
 * 小票
 */

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.TicketDAO;
import com.soft1841.cn.entity.Detail;

import java.sql.SQLException;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {

    @Override
    public List<Entity> getAllTicket() throws SQLException {
        return Db.use().query("SELECT * FROM t_ticket");
    }

    @Override
    public Entity getTicketById(int id) throws SQLException {
        return Db.use().queryOne("SELECT * FROM t_ticket WHERE id = ? ", id);
    }

    @Override
    public Long insertTicket(Detail detail) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_ticket")
                        .set("sellerID", detail.getTicketID())//根据cashier_id增加
        );
    }
}
