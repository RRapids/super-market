package com.soft1841.cn.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.soft1841.cn.dao.DetailDAO;
import com.soft1841.cn.entity.Detail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 小票明细DAO
 *
 * @author 汤越
 */
public class DetailDAOImpl implements DetailDAO {
    @Override
    public List<Detail> getAllDetails() throws SQLException {
        //查询得到List<Entity>
        List<Entity> entityList = Db.use().query("SELECT * FROM t_detail ");
        //创建一个List<Type>，存放具体的图书类别
        List<Detail> detailList = new ArrayList<>();
        //遍历entityList，转换为typeList
        for (Entity entity : entityList) {
            detailList.add(convertDetail(entity));
        }
        return detailList;
    }

    @Override
    public Detail getDetailById(long id) throws SQLException {
        Entity entity = Db.use().queryOne("SELECT * FROM t_detail WHERE detail_id = ? ",id);
        return convertDetail(entity);
    }

    @Override
    public Long insertDetail(Detail detail) throws SQLException {
        return Db.use().insertForGeneratedKey(
                Entity.create("t_detail")
                        .set("ticket_id", detail.getTicketID())
                        .set("barCode", detail.getBarCode())
                        .set("number", detail.getNumber())
        );
    }

    /**
     * 将Entity转换为Type类型
     *
     * @param entity
     * @return Type
     */
    private Detail convertDetail(Entity entity) {
        Detail detail = new Detail();
        detail.setId(entity.getLong("detail_id"));
        detail.setNumber(entity.getStr("ticket_id"));
        detail.setBarCode(entity.getStr("barCode"));
        detail.setTicketID(entity.getLong("ticket_id"));
        return detail;
    }
}
