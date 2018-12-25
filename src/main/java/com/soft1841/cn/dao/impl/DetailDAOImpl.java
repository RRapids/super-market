package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.DetailDAO;
import com.soft1841.cn.entity.Detail;

import java.sql.SQLException;
import java.util.List;
/**
 * 小票明细DAO
 * @author 汤越
 */
public class DetailDAOImpl implements DetailDAO {
    @Override
    public List<Entity> getAllDetails() throws SQLException {
        return Db.use().query("SELECT * FROM t_details ");
    }

    @Override
    public int getDetailById(int id) throws SQLException {
        return Db.use().del(
                Entity.create("t_details").set("id", id)
        );
    }

    @Override
    public Long insertDetail(Detail detail) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_details")
                        .set("ticketID", detail.getTicketID())
                        .set("barCode", detail.getBarCode())
                        .set("number", detail.getNumber())
        );
    }
}
