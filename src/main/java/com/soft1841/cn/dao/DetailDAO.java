package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Detail;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;

public interface DetailDAO {
    /**
     * 查所有
     * @return
     */
    List<Detail> getAllDetails() throws SQLException;

    /**
     * 根据id查询类别信息
     * @param id
     * @return
     */
    Entity getDetailById(int id) throws SQLException;

    /**
     * 新增类别, 返回自增主键(Long)
     * @param detail
     * @return
     */
    Long insertDetail(Detail detail) throws SQLException;
}
