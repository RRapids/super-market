package com.soft1841.cn.service;

import com.soft1841.cn.entity.Detail;

import java.util.List;

/**
 * 小票明细的业务逻辑接口
 * @author Yue Tang
 */
public interface DetailService {
    /**
     * 获取所有小票明细
     * @return
     */
    List<Detail> getAllDetails();

    /**
     * 根据id查询明细
     * @param id
     * @return
     */
    Detail getDetailById(long id);

    /**
     * 添加明细
     * @param detail
     */
    Long insertDetail(Detail detail);
}
