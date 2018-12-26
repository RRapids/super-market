package com.soft1841.cn.service;

import com.soft1841.cn.entity.Type;

import java.util.List;

/**
 * 商品类别的业务逻辑接口
 * @author 汤越
 */
public interface TypeService {
    /**
     * 获取所有商品类别的功能
     * @return
     */
    List<Type> selectAllTypes();

    /**
     * 根据id查询类别
     * @param id
     * @return
     */
    Type getTypeById(long id);

    /**
     * 添加类别
     * @param type
     */
    Long insertType(Type type);

    /**
     * 删除类别
     * @param id
     */
    void deleteTypeById(long id);
}
