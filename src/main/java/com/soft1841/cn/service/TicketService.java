package com.soft1841.cn.service;

import cn.hutool.db.Entity;
import com.soft1841.cn.entity.Detail;

import java.util.List;

/**
 * 小票的业务逻辑接口
 */
public interface TicketService {
    /**
     * 查所有
     * @return
     */
    List<Entity> getAllTicket();

    /**
     * 根据id查询类别信息
     * @param id
     * @return
     */
    Entity getTicketById(int id);

    /**
     * 新增类别, 返回自增主键(Long)
     * @param detail
     * @return
     */
    Long insertTicket(Detail detail);

}
